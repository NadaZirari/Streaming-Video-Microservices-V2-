package com.streaming.videoservice.services;

import com.streaming.videoservice.dtos.VideoDTO;
import com.streaming.videoservice.entities.Video;
import com.streaming.videoservice.mappers.VideoMapper;
import com.streaming.videoservice.repositories.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private VideoMapper videoMapper;

    @InjectMocks
    private VideoService videoService;

    private Video video;
    private VideoDTO videoDTO;

    @BeforeEach
    void setUp() {
        video = Video.builder()
                .id(1L)
                .title("Inception")
                .description("A mind-bending thriller")
                .type(Video.VideoType.FILM)
                .category(Video.Category.SCIENCE_FICTION)
                .build();

        videoDTO = VideoDTO.builder()
                .id(1L)
                .title("Inception")
                .description("A mind-bending thriller")
                .type(Video.VideoType.FILM)
                .category(Video.Category.SCIENCE_FICTION)
                .build();
    }

    @Test
    void getAllVideos_ShouldReturnList() {
        when(videoRepository.findAll()).thenReturn(Collections.singletonList(video));
        when(videoMapper.toDTO(video)).thenReturn(videoDTO);

        List<VideoDTO> result = videoService.getAllVideos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Inception");
        verify(videoRepository, times(1)).findAll();
    }

    @Test
    void getVideoById_ShouldReturnVideo() {
        when(videoRepository.findById(1L)).thenReturn(Optional.of(video));
        when(videoMapper.toDTO(video)).thenReturn(videoDTO);

        VideoDTO result = videoService.getVideoById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Inception");
        verify(videoRepository, times(1)).findById(1L);
    }

    @Test
    void createVideo_ShouldReturnSavedVideo() {
        when(videoMapper.toEntity(videoDTO)).thenReturn(video);
        when(videoRepository.save(video)).thenReturn(video);
        when(videoMapper.toDTO(video)).thenReturn(videoDTO);

        VideoDTO result = videoService.createVideo(videoDTO);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Inception");
        verify(videoRepository, times(1)).save(any(Video.class));
    }

    @Test
    void deleteVideo_ShouldInvokeRepository() {
        doNothing().when(videoRepository).deleteById(1L);

        videoService.deleteVideo(1L);

        verify(videoRepository, times(1)).deleteById(1L);
    }
}
