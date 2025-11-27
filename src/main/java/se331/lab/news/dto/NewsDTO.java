package se331.lab.news.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se331.lab.news.entity.Vote;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {
    private Long id;
    private String topic;
    private String shortDescription;
    private String fullContent;
    private String reporter;
    private LocalDateTime reportedAt;
    private String imageUrl;
    private List<Vote> votes; // Include votes for detail view

    // Statistics fields
    private int fakeCount;
    private int nonFakeCount;
    private String status; // "fake", "non-fake", "pending"
    private int fakePct;
    private int nonFakePct;
}
