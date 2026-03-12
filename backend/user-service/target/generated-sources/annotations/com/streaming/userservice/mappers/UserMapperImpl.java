package com.streaming.userservice.mappers;

import com.streaming.userservice.dtos.UserDTO;
import com.streaming.userservice.dtos.WatchHistoryDTO;
import com.streaming.userservice.dtos.WatchlistDTO;
import com.streaming.userservice.entities.User;
import com.streaming.userservice.entities.WatchHistory;
import com.streaming.userservice.entities.Watchlist;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-12T16:35:35+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.username( user.getUsername() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );

        return userDTO.build();
    }

    @Override
    public User toUserEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDTO.getId() );
        user.username( userDTO.getUsername() );
        user.email( userDTO.getEmail() );
        user.password( userDTO.getPassword() );

        return user.build();
    }

    @Override
    public WatchlistDTO toWatchlistDTO(Watchlist watchlist) {
        if ( watchlist == null ) {
            return null;
        }

        WatchlistDTO.WatchlistDTOBuilder watchlistDTO = WatchlistDTO.builder();

        watchlistDTO.id( watchlist.getId() );
        watchlistDTO.userId( watchlist.getUserId() );
        watchlistDTO.videoId( watchlist.getVideoId() );
        watchlistDTO.addedAt( watchlist.getAddedAt() );

        return watchlistDTO.build();
    }

    @Override
    public Watchlist toWatchlistEntity(WatchlistDTO watchlistDTO) {
        if ( watchlistDTO == null ) {
            return null;
        }

        Watchlist.WatchlistBuilder watchlist = Watchlist.builder();

        watchlist.id( watchlistDTO.getId() );
        watchlist.userId( watchlistDTO.getUserId() );
        watchlist.videoId( watchlistDTO.getVideoId() );
        watchlist.addedAt( watchlistDTO.getAddedAt() );

        return watchlist.build();
    }

    @Override
    public WatchHistoryDTO toWatchHistoryDTO(WatchHistory watchHistory) {
        if ( watchHistory == null ) {
            return null;
        }

        WatchHistoryDTO.WatchHistoryDTOBuilder watchHistoryDTO = WatchHistoryDTO.builder();

        watchHistoryDTO.id( watchHistory.getId() );
        watchHistoryDTO.userId( watchHistory.getUserId() );
        watchHistoryDTO.videoId( watchHistory.getVideoId() );
        watchHistoryDTO.watchedAt( watchHistory.getWatchedAt() );
        watchHistoryDTO.progressTime( watchHistory.getProgressTime() );
        watchHistoryDTO.completed( watchHistory.isCompleted() );

        return watchHistoryDTO.build();
    }

    @Override
    public WatchHistory toWatchHistoryEntity(WatchHistoryDTO watchHistoryDTO) {
        if ( watchHistoryDTO == null ) {
            return null;
        }

        WatchHistory.WatchHistoryBuilder watchHistory = WatchHistory.builder();

        watchHistory.id( watchHistoryDTO.getId() );
        watchHistory.userId( watchHistoryDTO.getUserId() );
        watchHistory.videoId( watchHistoryDTO.getVideoId() );
        watchHistory.watchedAt( watchHistoryDTO.getWatchedAt() );
        watchHistory.progressTime( watchHistoryDTO.getProgressTime() );
        watchHistory.completed( watchHistoryDTO.isCompleted() );

        return watchHistory.build();
    }
}
