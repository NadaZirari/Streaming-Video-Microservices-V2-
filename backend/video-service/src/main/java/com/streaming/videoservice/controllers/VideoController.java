package com.streaming.videoservice.controllers;

import com.streaming.videoservice.dtos.VideoDTO;
import com.streaming.videoservice.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping
    public List<VideoDTO> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/{id}")
    public VideoDTO getVideoById(@PathVariable Long id) {
        return videoService.getVideoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VideoDTO createVideo(@RequestBody VideoDTO videoDTO) {
        return videoService.createVideo(videoDTO);
    }

    @PutMapping("/{id}")
    public VideoDTO updateVideo(@PathVariable Long id, @RequestBody VideoDTO videoDTO) {
        return videoService.updateVideo(id, videoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
    }
}
