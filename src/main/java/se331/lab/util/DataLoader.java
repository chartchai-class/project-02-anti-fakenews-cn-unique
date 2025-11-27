package se331.lab.util;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se331.lab.news.entity.News;
import se331.lab.news.entity.Vote;
import se331.lab.news.repository.NewsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final NewsRepository newsRepository;

    public DataLoader(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (newsRepository.count() == 0) { // Only load data if repository is empty
            List<String> reporters = Arrays.asList("Alice Zhang", "Bob Li", "Carol Wang", "David Chen", "Emma Liu");
            List<String> topics = Arrays.asList(
                    "City Water Outage Rumor", "Celebrity Divorce Rumor", "New Virus Spreading News",
                    "School Closure Notice Authenticity", "Food Shortage Rumor", "Bridge Collapse Incident"
            );

            for (int i = 0; i < 5; i++) {
                News news = new News();
                news.setTopic(topics.get(i % topics.size()) + " (" + (i + 1) + ")");
                news.setShortDescription("Summary: This news needs community voting to judge authenticity.");
                news.setFullContent("Details: Please judge based on official notices, authoritative media reports, on-site photos or videos, and explain your reasoning in comments.");
                news.setReporter(reporters.get(i % reporters.size()));
                news.setReportedAt(LocalDateTime.now().minusDays(5 - i));
                news.setImageUrl("/images/southeast.jpg");

                // Add some votes
                List<Vote> votes = new ArrayList<>();
                votes.add(new Vote(null, "UserA", true, "Source is dubious.", "", LocalDateTime.now().minusDays(5 - i).minusHours(1), news));
                votes.add(new Vote(null, "UserB", false, "Officials have clarified the matter.", "", LocalDateTime.now().minusDays(5 - i).minusHours(2), news));
                votes.add(new Vote(null, "UserC", true, "Image may be edited.", "", LocalDateTime.now().minusDays(5 - i).minusHours(3), news));
                if (i % 2 == 0) { // Add some more for variety
                    votes.add(new Vote(null, "UserD", false, "This looks credible.", "", LocalDateTime.now().minusDays(5 - i).minusHours(4), news));
                }

                votes.forEach(news::addVote); // Properly set bidirectional relationship
                newsRepository.save(news);
            }
        }
    }
}
