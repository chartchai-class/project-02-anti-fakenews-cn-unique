package se331.lab.news.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequestDTO {
    private Boolean isFake;
    private String comment;
    private String imageUrl; // Optional
    private String voter;    // Optional, defaults to "Anonymous"
}
