import axiosInstance from './axios-instance';

export interface SongDTO {
  id?: number; // Opcional para nuevas canciones
  title: string;
  artist: string;
  genre: string;
}

export const getSongs = async (): Promise<SongDTO[]> => {
  const response = await axiosInstance.get('/songs');
  return response.data;
};

export const getSongById = async (id: number): Promise<SongDTO> => {
  const response = await axiosInstance.get(`/songs/${id}`);
  return response.data;
};

export const createSong = async (song: Omit<SongDTO, 'id'>): Promise<SongDTO> => {
  const response = await axiosInstance.post('/songs', song);
  return response.data;
};

export const updateSong = async (id: number, song: Omit<SongDTO, 'id'>): Promise<SongDTO> => {
  const response = await axiosInstance.put(`/songs/${id}`, song);
  return response.data;
};

export const deleteSong = async (id: number): Promise<void> => {
  await axiosInstance.delete(`/songs/${id}`);
};
