import api from './api';
import type { User } from '../types';

export interface AuthResponse {
  token: string;
  user: User;
}

export const userService = {
  login: async (credentials: any) => {
    const response = await api.post<AuthResponse>('/user-service/auth/login', credentials);
    if (response.data.token) {
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('user', JSON.stringify(response.data.user));
    }
    return response.data;
  },

  register: async (userData: any) => {
    const response = await api.post<AuthResponse>('/user-service/auth/register', userData);
    return response.data;
  },

  getCurrentUser: async () => {
    const response = await api.get<User>('/user-service/users/me');
    return response.data;
  },

  updateProfile: async (userData: any) => {
    const response = await api.put<User>('/user-service/users/me', userData);
    return response.data;
  },

  logout: () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }
};
