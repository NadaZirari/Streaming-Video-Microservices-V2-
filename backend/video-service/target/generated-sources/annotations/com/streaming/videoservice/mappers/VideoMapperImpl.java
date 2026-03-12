package com.streaming.videoservice.mappers;

import com.streaming.videoservice.dtos.VideoDTO;
import com.streaming.videoservice.entities.Video;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-05T13:40:51+0000",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class VideoMapperImpl implements VideoMapper {

    @Override
    public VideoDTO toDTO(Video video) {
        if ( video == null ) {
            return null;
        }

        VideoDTO.VideoDTOBuilder videoDTO = VideoDTO.builder();

        videoDTO.cast( video.getCast() );
        videoDTO.category( video.getCategory() );
        videoDTO.description( video.getDescription() );
        videoDTO.director( video.getDirector() );
        videoDTO.duration( video.getDuration() );
        videoDTO.id( video.getId() );
        videoDTO.rating( video.getRating() );
        videoDTO.releaseYear( video.getReleaseYear() );
        videoDTO.thumbnailUrl( video.getThumbnailUrl() );
        videoDTO.title( video.getTitle() );
        videoDTO.trailerUrl( video.getTrailerUrl() );
        videoDTO.type( video.getType() );

        return videoDTO.build();
    }

    @Override
    public Video toEntity(VideoDTO videoDTO) {
        if ( videoDTO == null ) {
            return null;
        }

        Video.VideoBuilder video = Video.builder();

        video.cast( videoDTO.getCast() );
        video.category( videoDTO.getCategory() );
        video.description( videoDTO.getDescription() );
        video.director( videoDTO.getDirector() );
        video.duration( videoDTO.getDuration() );
        video.id( videoDTO.getId() );
        video.rating( videoDTO.getRating() );
        video.releaseYear( videoDTO.getReleaseYear() );
        video.thumbnailUrl( videoDTO.getThumbnailUrl() );
        video.title( videoDTO.getTitle() );
        video.trailerUrl( videoDTO.getTrailerUrl() );
        video.type( videoDTO.getType() );

        return video.build();
    }
}
