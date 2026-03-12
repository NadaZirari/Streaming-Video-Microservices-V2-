package com.streaming.videoservice.mappers;

import com.streaming.videoservice.dtos.VideoDTO;
import com.streaming.videoservice.entities.Video;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    VideoDTO toDTO(Video video);
    Video toEntity(VideoDTO videoDTO);
}
