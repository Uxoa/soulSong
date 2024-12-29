import axiosInstance from "./axios-instance";
import { Profile, ProfileDTO } from "../types";

const profileService = {
  getProfiles: async (): Promise<Profile[]> => {
    const response = await axiosInstance.get<Profile[]>("/profiles");
    return response.data;
  },
  getProfile: async (id: number): Promise<ProfileDTO> => {
    const response = await axiosInstance.get<ProfileDTO>(`/profiles/${id}`);
    return response.data; // Devuelve directamente el objeto ProfileDTO
  },
  createProfile: async (profile: Omit<ProfileDTO, "id">): Promise<ProfileDTO> => {
    const response = await axiosInstance.post<ProfileDTO>("/profiles", profile);
    return response.data;
  },
  updateProfile: async (id: number, profile: Omit<ProfileDTO, "id">): Promise<ProfileDTO> => {
    const response = await axiosInstance.put<ProfileDTO>(`/profiles/${id}`, profile);
    return response.data;
  },
  deleteProfile: async (id: number): Promise<void> => {
    await axiosInstance.delete(`/profiles/${id}`);
  },
};

export default profileService;
