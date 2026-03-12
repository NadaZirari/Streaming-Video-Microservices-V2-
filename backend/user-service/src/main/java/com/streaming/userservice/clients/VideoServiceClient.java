package com.streaming.userservice.clients;

import com.streaming.userservice.dtos.VideoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "video-service")
public interface VideoServiceClient {
    @GetMapping("/api/videos/{id}")
    VideoDTO getVideoById(@PathVariable("id") Long id);
}
