import axiosInstance from "./axios-instance";
import { User } from "../types";

// URL base del endpoint en tu backend
const API_URL = "/users";

// Obtener todos los usuarios
export const fetchUsers = async (): Promise<User[]> => {
  const response = await axiosInstance.get<User[]>(API_URL);
  return response.data;
};

// Agregar un nuevo usuario
export const addUser = async (user: Omit<User, "id">): Promise<User> => {
  const response = await axiosInstance.post<User>(API_URL, user);
  return response.data;
};

// Actualizar un usuario existente
export const updateUser = async (
    id: string,
    updatedUser: Partial<User>
): Promise<User> => {
  const response = await axiosInstance.put<User>(
      `${API_URL}/${id}`,
      updatedUser
  );
  return response.data;
};

// Eliminar un usuario
export const deleteUser = async (id: string): Promise<void> => {
  await axiosInstance.delete(`${API_URL}/${id}`);
};

// Obtener un usuario por ID
export const getUserById = async (id: string): Promise<User> => {
  const response = await axiosInstance.get<User>(`${API_URL}/${id}`);
  return response.data;
};
