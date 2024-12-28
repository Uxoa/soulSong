export interface User {
  id: number;
  username: string;
  email: string;
  profile?: ProfileDTO; // Perfil opcional asociado al usuario
  favoriteSongs: string[];
}

export interface UserDTO {
  id?: number;
  name: string;
  email: string;
  favoriteSongs: string[];
}
export interface SongDTO {
  id: number;
  name: string;
  description: string;
}

export interface Profile {
  id: number;
  name: string;
  avatarUrl?: string;
  favoriteSong?: string;
}

export interface ProfileDTO {
  name: string;
  email: string;
  imageUrl?: string;
  bio?: string;
  avatar: string;
  songs: SongDTO[];
}
