package se331.lab.news.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "votes") // Exclude votes from equals/hashCode to prevent recursion and performance issues
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String shortDescription; // Renamed from 'short' to avoid keyword conflict
    @Column(length = 1000) // Assuming 'full' content can be long
    private String fullContent;    // Renamed from 'full' to avoid keyword conflict
    private String reporter;
    private LocalDateTime reportedAt;
    private String imageUrl; // Optional

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    // Helper method to add vote and set bidirectional relationship
    public void addVote(Vote vote) {
        votes.add(vote);
        vote.setNews(this);
    }
}
