package se331.lab.news.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.news.dto.NewsDTO;
import se331.lab.news.dto.NewsRequestDTO;
import se331.lab.news.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<NewsDTO> getAllNews() {
        return newsService.getAllNewsWithStats();
    }

    @GetMapping("/{id}")
    public NewsDTO getNewsById(@PathVariable Long id) {
        NewsDTO news = newsService.getNewsById(id);
        if (news == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with ID: " + id);
        }
        return news;
    }

    @PostMapping
    public ResponseEntity<NewsDTO> addNews(@RequestBody NewsRequestDTO newsRequest) {
        return ResponseEntity.ok(newsService.addNews(newsRequest));
    }
}
