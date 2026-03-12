package com.streaming.userservice.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchHistoryDTO {
    private Long id;
    private Long userId;
    private Long videoId;
    private LocalDateTime watchedAt;
    private String progressTime;
    private boolean completed;
}
