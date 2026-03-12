package com.streaming.userservice.mappers;

import com.streaming.userservice.dtos.UserDTO;
import com.streaming.userservice.dtos.WatchlistDTO;
import com.streaming.userservice.dtos.WatchHistoryDTO;
import com.streaming.userservice.entities.User;
import com.streaming.userservice.entities.Watchlist;
import com.streaming.userservice.entities.WatchHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDTO toUserDTO(User user);
    User toUserEntity(UserDTO userDTO);
    
    WatchlistDTO toWatchlistDTO(Watchlist watchlist);
    Watchlist toWatchlistEntity(WatchlistDTO watchlistDTO);
    
    WatchHistoryDTO toWatchHistoryDTO(WatchHistory watchHistory);
    WatchHistory toWatchHistoryEntity(WatchHistoryDTO watchHistoryDTO);
}
