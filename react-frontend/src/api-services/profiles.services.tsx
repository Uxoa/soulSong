import axiosInstance from "./axios-instance";
import { Profile, ProfileDTO } from "../types";

const profileService = {
  getProfiles: async (): Promise<Profile[]> => {
    const response = await axiosInstance.get<Profile[]>("/profiles");
    return response.data.map((profileDTO: ProfileDTO) => ({
      id: profileDTO.id,
      name: profileDTO.userName,
      avatarUrl: profileDTO.avatarUrl || "/images/default-avatar.png", // Avatar predeterminado
      email: profileDTO.user?.email || "No email available",
      songs: profileDTO.songs?.map((song) => ({
        id: song.id,
        name: song.songEssence?.songName || "Unknown Song",
        description: song.songEssence?.description || "",
      })) || [],
    }));
  },
  getProfile: async (id: number): Promise<ProfileDTO> => {
    const response = await axiosInstance.get<ProfileDTO>(`/profiles/${id}`);
    return response.data;
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
