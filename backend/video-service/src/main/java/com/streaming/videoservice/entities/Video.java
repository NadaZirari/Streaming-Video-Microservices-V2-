package com.streaming.videoservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private String thumbnailUrl;
    private String trailerUrl;
    private String duration;
    private int releaseYear;
    
    @Enumerated(EnumType.STRING)
    private VideoType type;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    private Double rating;
    private String director;
    private String cast;

    public enum VideoType {
        FILM, SERIE
    }

    public enum Category {
        ACTION, COMEDIE, DRAME, SCIENCE_FICTION, THRILLER, HORREUR
    }
}
