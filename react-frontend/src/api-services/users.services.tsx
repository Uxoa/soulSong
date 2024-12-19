import { User } from '../types';
import axiosInstance from './axios-instance';


export interface UserDTO {
  id?: number;
  name: string;
  email: string;
  favoriteSongs: string[];
}

export const getUsers = async (): Promise<User[]> => {
  const response = await axiosInstance.get("/users");
  return response.data;
};

export const getUserById = async (id: string): Promise<User> => {
  const response = await axiosInstance.get(`/users/${id}`);
  return response.data; // Aseguramos que `response.data` cumple con el tipo `User`.
};

export const createUser = async (user: Omit<UserDTO, 'id'>): Promise<UserDTO> => {
  const response = await axiosInstance.post('/users', user);
  return response.data;
};

export const updateUser = async (id: number, user: Omit<UserDTO, 'id'>): Promise<UserDTO> => {
  const response = await axiosInstance.put(`/users/${id}`, user);
  return response.data;
};

export const deleteUser = async (id: number): Promise<void> => {
  await axiosInstance.delete(`/users/${id}`);
};
