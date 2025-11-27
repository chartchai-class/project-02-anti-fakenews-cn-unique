package se331.lab.news.service;

import org.springframework.stereotype.Service;
import se331.lab.news.dto.NewsDTO;
import se331.lab.news.dto.NewsRequestDTO;
import se331.lab.news.entity.News;
import se331.lab.news.repository.NewsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<NewsDTO> getAllNewsWithStats() {
        return newsRepository.findAll().stream()
                .map(this::convertToNewsDTOWithStats)
                .collect(Collectors.toList());
    }

    public NewsDTO getNewsById(Long id) {
        return newsRepository.findById(id)
                .map(this::convertToNewsDTOWithStats)
                .orElse(null); // Or throw an exception for Not Found
    }

    public NewsDTO addNews(NewsRequestDTO newsRequest) {
        News news = new News();
        news.setTopic(newsRequest.getTopic());
        news.setShortDescription(newsRequest.getShortDescription());
        news.setFullContent(newsRequest.getFullContent());
        news.setReporter(newsRequest.getReporter() != null && !newsRequest.getReporter().isEmpty() ? newsRequest.getReporter() : "Anonymous Reporter");
        news.setReportedAt(LocalDateTime.now());
        news.setImageUrl(newsRequest.getImageUrl());
        news.setVotes(new ArrayList<>()); // Initialize votes list

        News savedNews = newsRepository.save(news);
        return convertToNewsDTOWithStats(savedNews);
    }

    private NewsDTO convertToNewsDTOWithStats(News news) {
        int fake = news.getVotes().stream().filter(v -> v.getIsFake()).collect(Collectors.toList()).size();
        int nonFake = news.getVotes().size() - fake;
        String status = "";
        if (fake > nonFake) {
            status = "fake";
        } else if (nonFake > fake) {
            status = "non-fake";
        } else {
            status = "pending";
        }

        int fakePct = news.getVotes().isEmpty() ? 0 : (int) Math.round((double) fake / news.getVotes().size() * 100);
        int nonFakePct = news.getVotes().isEmpty() ? 0 : (int) Math.round((double) nonFake / news.getVotes().size() * 100);

        return NewsDTO.builder()
                .id(news.getId())
                .topic(news.getTopic())
                .shortDescription(news.getShortDescription())
                .fullContent(news.getFullContent())
                .reporter(news.getReporter())
                .reportedAt(news.getReportedAt())
                .imageUrl(news.getImageUrl())
                .votes(news.getVotes()) // For now, we include full votes list, but could be DTOs too
                .fakeCount(fake)
                .nonFakeCount(nonFake)
                .status(status)
                .fakePct(fakePct)
                .nonFakePct(nonFakePct)
                .build();
    }
}
