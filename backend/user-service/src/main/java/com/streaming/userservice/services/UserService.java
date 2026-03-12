package com.streaming.userservice.services;

import com.streaming.userservice.clients.VideoServiceClient;
import com.streaming.userservice.dtos.*;
import com.streaming.userservice.entities.*;
import com.streaming.userservice.mappers.UserMapper;
import com.streaming.userservice.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final WatchlistRepository watchlistRepository;
    private final WatchHistoryRepository watchHistoryRepository;
    private final UserMapper userMapper;
    private final VideoServiceClient videoServiceClient;

    // User CRUD
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        return userMapper.toUserDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toUserEntity(userDTO);
        return userMapper.toUserDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Watchlist Logic
    public WatchlistDTO addToWatchlist(Long userId, Long videoId) {
        // Check if user exists
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        // Use Feign to check if video exists in video-service
        VideoDTO video = videoServiceClient.getVideoById(videoId);
        if (video == null) {
            throw new RuntimeException("Video not found in video-service: " + videoId);
        }

        Watchlist watchlist = Watchlist.builder()
                .userId(userId)
                .videoId(videoId)
                .addedAt(LocalDateTime.now())
                .build();
        
        return userMapper.toWatchlistDTO(watchlistRepository.save(watchlist));
    }

    public void removeFromWatchlist(Long userId, Long videoId) {
        watchlistRepository.deleteByUserIdAndVideoId(userId, videoId);
    }

    public List<WatchlistDTO> getUserWatchlist(Long userId) {
        return watchlistRepository.findByUserId(userId).stream()
                .map(userMapper::toWatchlistDTO)
                .collect(Collectors.toList());
    }

    // Watch History Logic
    public WatchHistoryDTO recordWatchHistory(Long userId, WatchHistoryDTO historyDTO) {
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        // Check video existence
        videoServiceClient.getVideoById(historyDTO.getVideoId());

        WatchHistory history = userMapper.toWatchHistoryEntity(historyDTO);
        history.setUserId(userId);
        history.setWatchedAt(LocalDateTime.now());
        
        return userMapper.toWatchHistoryDTO(watchHistoryRepository.save(history));
    }

    public List<WatchHistoryDTO> getUserHistory(Long userId) {
        return watchHistoryRepository.findByUserId(userId).stream()
                .map(userMapper::toWatchHistoryDTO)
                .collect(Collectors.toList());
    }

    // Statistics
    public Map<String, Object> getUserStats(Long userId) {
        List<Watchlist> watchlist = watchlistRepository.findByUserId(userId);
        List<WatchHistory> history = watchHistoryRepository.findByUserId(userId);

        Map<String, Object> stats = new HashMap<>();
        stats.put("userId", userId);
        stats.put("watchlistCount", watchlist.size());
        stats.put("watchedVideosCount", history.size());
        stats.put("completedVideosCount", history.stream().filter(WatchHistory::isCompleted).count());
        
        return stats;
    }
}
