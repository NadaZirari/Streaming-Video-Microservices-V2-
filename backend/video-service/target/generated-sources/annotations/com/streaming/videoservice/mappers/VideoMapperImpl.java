package com.streaming.videoservice.mappers;

import com.streaming.videoservice.dtos.VideoDTO;
import com.streaming.videoservice.entities.Video;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-12T16:35:31+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class VideoMapperImpl implements VideoMapper {

    @Override
    public VideoDTO toDTO(Video video) {
        if ( video == null ) {
            return null;
        }

        VideoDTO.VideoDTOBuilder videoDTO = VideoDTO.builder();

        videoDTO.id( video.getId() );
        videoDTO.title( video.getTitle() );
        videoDTO.description( video.getDescription() );
        videoDTO.thumbnailUrl( video.getThumbnailUrl() );
        videoDTO.trailerUrl( video.getTrailerUrl() );
        videoDTO.duration( video.getDuration() );
        videoDTO.releaseYear( video.getReleaseYear() );
        videoDTO.type( video.getType() );
        videoDTO.category( video.getCategory() );
        videoDTO.rating( video.getRating() );
        videoDTO.director( video.getDirector() );
        videoDTO.cast( video.getCast() );

        return videoDTO.build();
    }

    @Override
    public Video toEntity(VideoDTO videoDTO) {
        if ( videoDTO == null ) {
            return null;
        }

        Video.VideoBuilder video = Video.builder();

        video.id( videoDTO.getId() );
        video.title( videoDTO.getTitle() );
        video.description( videoDTO.getDescription() );
        video.thumbnailUrl( videoDTO.getThumbnailUrl() );
        video.trailerUrl( videoDTO.getTrailerUrl() );
        video.duration( videoDTO.getDuration() );
        video.releaseYear( videoDTO.getReleaseYear() );
        video.type( videoDTO.getType() );
        video.category( videoDTO.getCategory() );
        video.rating( videoDTO.getRating() );
        video.director( videoDTO.getDirector() );
        video.cast( videoDTO.getCast() );

        return video.build();
    }
}
