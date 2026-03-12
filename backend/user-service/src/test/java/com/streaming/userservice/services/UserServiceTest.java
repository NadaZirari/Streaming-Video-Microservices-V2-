package com.streaming.userservice.services;

import com.streaming.userservice.clients.VideoServiceClient;
import com.streaming.userservice.dtos.*;
import com.streaming.userservice.entities.*;
import com.streaming.userservice.mappers.UserMapper;
import com.streaming.userservice.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private WatchlistRepository watchlistRepository;
    @Mock
    private WatchHistoryRepository watchHistoryRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private VideoServiceClient videoServiceClient;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_ShouldReturnSavedUser() {
        UserDTO userDTO = UserDTO.builder().username("testuser").build();
        User user = User.builder().id(1L).username("testuser").build();

        when(userMapper.toUserEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.createUser(userDTO);

        assertThat(result).isNotNull();
        verify(userRepository).save(any(User.class));
    }

    @Test
    void addToWatchlist_ShouldSaveWatchlist() {
        Long userId = 1L;
        Long videoId = 10L;
        User user = User.builder().id(userId).build();
        VideoDTO videoDTO = VideoDTO.builder().id(videoId).build();
        Watchlist watchlist = Watchlist.builder().userId(userId).videoId(videoId).build();
        WatchlistDTO watchlistDTO = WatchlistDTO.builder().userId(userId).videoId(videoId).build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(videoServiceClient.getVideoById(videoId)).thenReturn(videoDTO);
        when(watchlistRepository.save(any(Watchlist.class))).thenReturn(watchlist);
        when(userMapper.toWatchlistDTO(any(Watchlist.class))).thenReturn(watchlistDTO);

        WatchlistDTO result = userService.addToWatchlist(userId, videoId);

        assertThat(result.getVideoId()).isEqualTo(videoId);
        verify(watchlistRepository).save(any(Watchlist.class));
    }
}
