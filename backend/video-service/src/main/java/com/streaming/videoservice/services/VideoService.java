package com.streaming.videoservice.services;

import com.streaming.videoservice.dtos.VideoDTO;
import com.streaming.videoservice.entities.Video;
import com.streaming.videoservice.mappers.VideoMapper;
import com.streaming.videoservice.repositories.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    public List<VideoDTO> getAllVideos() {
        return videoRepository.findAll().stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public VideoDTO getVideoById(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found: " + id));
        return videoMapper.toDTO(video);
    }

    public VideoDTO createVideo(VideoDTO videoDTO) {
        Video video = videoMapper.toEntity(videoDTO);
        Video savedVideo = videoRepository.save(video);
        return videoMapper.toDTO(savedVideo);
    }

    public VideoDTO updateVideo(Long id, VideoDTO videoDTO) {
        Video existingVideo = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found: " + id));
        
        existingVideo.setTitle(videoDTO.getTitle());
        existingVideo.setDescription(videoDTO.getDescription());
        existingVideo.setThumbnailUrl(videoDTO.getThumbnailUrl());
        existingVideo.setTrailerUrl(videoDTO.getTrailerUrl());
        existingVideo.setDuration(videoDTO.getDuration());
        existingVideo.setReleaseYear(videoDTO.getReleaseYear());
        existingVideo.setType(videoDTO.getType());
        existingVideo.setCategory(videoDTO.getCategory());
        existingVideo.setRating(videoDTO.getRating());
        existingVideo.setDirector(videoDTO.getDirector());
        existingVideo.setCast(videoDTO.getCast());

        return videoMapper.toDTO(videoRepository.save(existingVideo));
    }

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }
}
