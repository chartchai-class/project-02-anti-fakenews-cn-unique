package se331.lab.news.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequestDTO {
    private String topic;
    private String shortDescription;
    private String fullContent;
    private String reporter;
    private String imageUrl; // Optional
}
