export interface SongEssence {
  id: number;
  songName: string;
  description: string;
  trackId: string;
  danceability: number;
  energy: number;
  tempo: number;
  valence: number;
}

export interface SongDTO {
  id: number;
  profileId: number;
  songTitle: string | null;
  songEssence: SongEssence | null;
}

export interface ProfileDTO {
  id: number;
  userName: string;
  avatarUrl: string;
  email: string;
  favoriteSong?: SongEssence;
  songs: Array<{ id: number; name: string; description: string }>;
  songEssences: SongEssence[];
}

export interface Profile {
  id: number;
  name: string;
  avatarUrl: string;
  email: string;
  favoriteSong?: SongEssence;
  songs: Array<{ id: number; name: string; description: string }>;
  songEssences: SongEssence[];
}


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

export interface UserLoginDTO {
  email: string;
  password: string;
}

