package com.streaming.userservice.controllers;

import com.streaming.userservice.dtos.*;
import com.streaming.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // User Endpoints
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Watchlist Endpoints
    @PostMapping("/{userId}/watchlist/{videoId}")
    public WatchlistDTO addToWatchlist(@PathVariable Long userId, @PathVariable Long videoId) {
        return userService.addToWatchlist(userId, videoId);
    }

    @DeleteMapping("/{userId}/watchlist/{videoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromWatchlist(@PathVariable Long userId, @PathVariable Long videoId) {
        userService.removeFromWatchlist(userId, videoId);
    }

    @GetMapping("/{userId}/watchlist")
    public List<WatchlistDTO> getWatchlist(@PathVariable Long userId) {
        return userService.getUserWatchlist(userId);
    }

    // History Endpoints
    @PostMapping("/{userId}/history")
    public WatchHistoryDTO recordHistory(@PathVariable Long userId, @RequestBody WatchHistoryDTO historyDTO) {
        return userService.recordWatchHistory(userId, historyDTO);
    }

    @GetMapping("/{userId}/history")
    public List<WatchHistoryDTO> getHistory(@PathVariable Long userId) {
        return userService.getUserHistory(userId);
    }

    // Stats Endpoints
    @GetMapping("/{userId}/stats")
    public Map<String, Object> getStats(@PathVariable Long userId) {
        return userService.getUserStats(userId);
    }
}
