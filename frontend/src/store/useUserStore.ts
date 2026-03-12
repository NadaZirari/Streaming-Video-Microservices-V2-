import { create } from 'zustand';
import { userService } from '../services/userService';
import type { User, WatchlistEntry, WatchHistoryEntry } from '../types';

interface UserState {
  currentUser: User | null;
  watchlist: WatchlistEntry[];
  history: WatchHistoryEntry[];
  loading: boolean;
  error: string | null;
  setCurrentUser: (user: User | null) => void;
  fetchWatchlist: (userId: string) => Promise<void>;
  fetchHistory: (userId: string) => Promise<void>;
  addToWatchlist: (userId: string, videoId: string) => Promise<void>;
  removeFromWatchlist: (userId: string, videoId: string) => Promise<void>;
}

export const useUserStore = create<UserState>((set) => ({
  currentUser: JSON.parse(localStorage.getItem('user') || 'null'),
  watchlist: [],
  history: [],
  loading: false,
  error: null,

  setCurrentUser: (user) => {
    set({ currentUser: user });
    if (user) {
      localStorage.setItem('user', JSON.stringify(user));
    } else {
      localStorage.removeItem('user');
    }
  },

  fetchWatchlist: async (userId) => {
    set({ loading: true, error: null });
    try {
      const watchlist = await userService.getWatchlist(userId);
      set({ watchlist, loading: false });
    } catch (error: any) {
      set({ error: error.message || 'Failed to fetch watchlist', loading: false });
    }
  },

  fetchHistory: async (userId) => {
    set({ loading: true, error: null });
    try {
      const history = await userService.getHistory(userId);
      set({ history, loading: false });
    } catch (error: any) {
      set({ error: error.message || 'Failed to fetch history', loading: false });
    }
  },

  addToWatchlist: async (userId, videoId) => {
    try {
      await userService.addToWatchlist(userId, videoId);
      const watchlist = await userService.getWatchlist(userId);
      set({ watchlist });
    } catch (error: any) {
      set({ error: error.message || 'Failed to add to watchlist' });
    }
  },

  removeFromWatchlist: async (userId, videoId) => {
    try {
      await userService.removeFromWatchlist(userId, videoId);
      const watchlist = await userService.getWatchlist(userId);
      set({ watchlist });
    } catch (error: any) {
      set({ error: error.message || 'Failed to remove from watchlist' });
    }
  }
}));
