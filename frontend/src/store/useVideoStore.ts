import { create } from 'zustand';
import { videoService } from '../services/videoService';
import type { Video } from '../types';

interface VideoState {
  videos: Video[];
  loading: boolean;
  error: string | null;
  fetchVideos: () => Promise<void>;
  searchVideos: (query: string) => Promise<void>;
  filterByCategory: (category: string) => Promise<void>;
  filterByType: (type: string) => Promise<void>;
}

export const useVideoStore = create<VideoState>((set) => ({
  videos: [],
  loading: false,
  error: null,

  fetchVideos: async () => {
    set({ loading: true, error: null });
    try {
      const videos = await videoService.getAllVideos();
      set({ videos, loading: false });
    } catch (error: any) {
      set({ error: error.message || 'Failed to fetch videos', loading: false });
    }
  },

  searchVideos: async (query: string) => {
    set({ loading: true, error: null });
    try {
      const videos = await videoService.searchVideos(query);
      set({ videos, loading: false });
    } catch (error: any) {
      set({ error: error.message || 'Search failed', loading: false });
    }
  },

  filterByCategory: async (category: string) => {
    set({ loading: true, error: null });
    try {
      if (category === 'Tous') {
        const videos = await videoService.getAllVideos();
        set({ videos, loading: false });
      } else {
        const videos = await videoService.getVideosByCategory(category);
        set({ videos, loading: false });
      }
    } catch (error: any) {
      set({ error: error.message || 'Filtering failed', loading: false });
    }
  },

  filterByType: async (type: string) => {
    set({ loading: true, error: null });
    try {
      const videos = await videoService.getVideosByType(type);
      set({ videos, loading: false });
    } catch (error: any) {
      set({ error: error.message || 'Filtering failed', loading: false });
    }
  }
}));
