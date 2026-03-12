import api from './api';
import type { User, WatchlistEntry, WatchHistoryEntry } from '../types';

export const userService = {
  // User Management
  getAllUsers: async () => {
    const response = await api.get<User[]>('/user-service/api/users');
    return response.data;
  },

  getUserById: async (id: string) => {
    const response = await api.get<User>(`/user-service/api/users/${id}`);
    return response.data;
  },

  createUser: async (userData: Partial<User>) => {
    const response = await api.post<User>('/user-service/api/users', userData);
    return response.data;
  },

  deleteUser: async (id: string) => {
    await api.delete(`/user-service/api/users/${id}`);
  },

  // Watchlist
  addToWatchlist: async (userId: string, videoId: string) => {
    const response = await api.post<WatchlistEntry>(`/user-service/api/users/${userId}/watchlist/${videoId}`);
    return response.data;
  },

  removeFromWatchlist: async (userId: string, videoId: string) => {
    await api.delete(`/user-service/api/users/${userId}/watchlist/${videoId}`);
  },

  getWatchlist: async (userId: string) => {
    const response = await api.get<WatchlistEntry[]>(`/user-service/api/users/${userId}/watchlist`);
    return response.data;
  },

  // History
  recordHistory: async (userId: string, historyData: Partial<WatchHistoryEntry>) => {
    const response = await api.post<WatchHistoryEntry>(`/user-service/api/users/${userId}/history`, historyData);
    return response.data;
  },

  getHistory: async (userId: string) => {
    const response = await api.get<WatchHistoryEntry[]>(`/user-service/api/users/${userId}/history`);
    return response.data;
  },

  // Stats
  getUserStats: async (userId: string) => {
    const response = await api.get<any>(`/user-service/api/users/${userId}/stats`);
    return response.data;
  }
};
