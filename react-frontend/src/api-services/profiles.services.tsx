import axiosInstance from './axios-instance';
import {Profile, ProfileDTO} from "../types";

/**
 * Obtener todos los perfiles.
 * @returns Lista de perfiles.
 */
export const getProfiles = async (): Promise<ProfileDTO[]> => {
  const response = await axiosInstance.get("/profiles");
  return response.data;
}

/**
 * Crear un nuevo perfil.
 * @param profile - Datos del perfil a crear.
 * @returns Perfil creado.
 */
export const createProfile = async (
    profile: Omit<ProfileDTO, 'id'>
): Promise<ProfileDTO> => {
  const response = await axiosInstance.post("/profiles", profile);
  return response.data;
};

/**
 * Actualizar un perfil existente.
 * @param id - ID del perfil a actualizar.
 * @param profile - Datos del perfil a actualizar.
 * @returns Perfil actualizado.
 */
export const updateProfile = async (
    id: number,
    profile: Omit<ProfileDTO, 'id'>
): Promise<ProfileDTO> => {
  const response = await axiosInstance.put(`/profiles/${id}`, profile);
  return response.data;
};

/**
 * Eliminar un perfil por ID.
 * @param id - ID del perfil a eliminar.
 */
export const deleteProfile = async (id: number): Promise<void> => {
  await axiosInstance.delete(`/profiles/${id}`);
};

export const profileService = {
  getProfiles: () => axiosInstance.get<Profile[]>("/profiles"),
  getProfile: (id: string) => axiosInstance.get<Profile>(`/profiles/${id}`),
  createProfile: (data: Profile) => axiosInstance.post<Profile>("/profiles", data),
  updateProfile: (id: string, data: Profile) => axiosInstance.put<Profile>(`/profiles/${id}`, data),
  deleteProfile: (id: string) => axiosInstance.delete<void>(`/profiles/${id}`),
};