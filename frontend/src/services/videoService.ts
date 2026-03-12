import api from './api';
import type { Video } from '../types';

export const videoService = {
  getAllVideos: async () => {
    const response = await api.get<Video[]>('/video-service/api/videos');
    return response.data;
  },

  getVideoById: async (id: string) => {
    const response = await api.get<Video>(`/video-service/api/videos/${id}`);
    return response.data;
  },

  searchVideos: async (query: string) => {
    const response = await api.get<Video[]>(`/video-service/api/videos/search?query=${query}`);
    return response.data;
  },

  getVideosByCategory: async (category: string) => {
    const response = await api.get<Video[]>(`/video-service/api/videos/category/${category}`);
    return response.data;
  },

  getVideosByType: async (type: string) => {
    const response = await api.get<Video[]>(`/video-service/api/videos/type/${type}`);
    return response.data;
  }
};
