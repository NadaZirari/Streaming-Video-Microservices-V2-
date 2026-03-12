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
    date = "2026-03-12T15:13:43+0000",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.email( user.getEmail() );
        userDTO.id( user.getId() );
        userDTO.password( user.getPassword() );
        userDTO.username( user.getUsername() );

        return userDTO.build();
    }

    @Override
    public User toUserEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( userDTO.getEmail() );
        user.id( userDTO.getId() );
        user.password( userDTO.getPassword() );
        user.username( userDTO.getUsername() );

        return user.build();
    }

    @Override
    public WatchlistDTO toWatchlistDTO(Watchlist watchlist) {
        if ( watchlist == null ) {
            return null;
        }

        WatchlistDTO.WatchlistDTOBuilder watchlistDTO = WatchlistDTO.builder();

        watchlistDTO.addedAt( watchlist.getAddedAt() );
        watchlistDTO.id( watchlist.getId() );
        watchlistDTO.userId( watchlist.getUserId() );
        watchlistDTO.videoId( watchlist.getVideoId() );

        return watchlistDTO.build();
    }

    @Override
    public Watchlist toWatchlistEntity(WatchlistDTO watchlistDTO) {
        if ( watchlistDTO == null ) {
            return null;
        }

        Watchlist.WatchlistBuilder watchlist = Watchlist.builder();

        watchlist.addedAt( watchlistDTO.getAddedAt() );
        watchlist.id( watchlistDTO.getId() );
        watchlist.userId( watchlistDTO.getUserId() );
        watchlist.videoId( watchlistDTO.getVideoId() );

        return watchlist.build();
    }

    @Override
    public WatchHistoryDTO toWatchHistoryDTO(WatchHistory watchHistory) {
        if ( watchHistory == null ) {
            return null;
        }

        WatchHistoryDTO.WatchHistoryDTOBuilder watchHistoryDTO = WatchHistoryDTO.builder();

        watchHistoryDTO.completed( watchHistory.isCompleted() );
        watchHistoryDTO.id( watchHistory.getId() );
        watchHistoryDTO.progressTime( watchHistory.getProgressTime() );
        watchHistoryDTO.userId( watchHistory.getUserId() );
        watchHistoryDTO.videoId( watchHistory.getVideoId() );
        watchHistoryDTO.watchedAt( watchHistory.getWatchedAt() );

        return watchHistoryDTO.build();
    }

    @Override
    public WatchHistory toWatchHistoryEntity(WatchHistoryDTO watchHistoryDTO) {
        if ( watchHistoryDTO == null ) {
            return null;
        }

        WatchHistory.WatchHistoryBuilder watchHistory = WatchHistory.builder();

        watchHistory.completed( watchHistoryDTO.isCompleted() );
        watchHistory.id( watchHistoryDTO.getId() );
        watchHistory.progressTime( watchHistoryDTO.getProgressTime() );
        watchHistory.userId( watchHistoryDTO.getUserId() );
        watchHistory.videoId( watchHistoryDTO.getVideoId() );
        watchHistory.watchedAt( watchHistoryDTO.getWatchedAt() );

        return watchHistory.build();
    }
}
