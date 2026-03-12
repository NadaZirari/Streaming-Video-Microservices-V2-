package com.streaming.videoservice.dtos;

import com.streaming.videoservice.entities.Video.Category;
import com.streaming.videoservice.entities.Video.VideoType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoDTO {
    private Long id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private String trailerUrl;
    private String duration;
    private int releaseYear;
    private VideoType type;
    private Category category;
    private Double rating;
    private String director;
    private String cast;
}
