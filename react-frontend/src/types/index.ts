export interface Song {
  id: number;
  name: string; // Nombre de la canción
  trackId: string;
  songs: Array<{ id: number; name: string }>; // List of songs
  song: Array<{ id: number; name: string }>; // Optional extra song array
}

export interface SongDTO {
  id: number;
  profileId: number;
  name: string; // Nombre de la canción
  trackId: string;
  songs: Array<{ id: number; name: string }>; // List of songs
  song: Array<{ id: number; name: string }>; // Optional extra song array
}

export interface SongEssence {
  id: number;
  songName: string;
  trackId: string;
  danceability: number;
  energy: number;
  tempo: number;
  valence: number;
  description?: string; // Campo opcional
}

export interface Profile {
  id: number;
  name: string;
  avatarUrl: string;
  email: string;
  song?: {
    id: number;
    songName: string;
    trackId: string; // Agregamos trackId aquí
    description?: string;
  };
  songEssences: SongEssence[]; // Lista de SongEssence
}


export interface ProfileDTO {
  id: number;
  userName: string;
  avatarUrl: string;
  email: string;
  songs: Array<{ id: number; name: string }>; // List of songs
  song: Array<{ id: number; name: string }>; // Optional extra song array
}

export interface SongEssenceResult {
  songEssence: {
    tempo: number;
    danceability: number;
    energy: number;
    valence: number;
  };
  description: string;
}



export interface User {
  id: number;
  username: string;
  avatarUrl: string;
  email: string;
  profile?: Profile; // Perfil opcional asociado al usuario
  songs: Array<{ id: number; name: string }>; // List of songs
  song: Array<{ id: number; name: string }>; // Optional extra song array
}

export interface UserDTO {
  id?: number;
  name: string;
  avatarUrl: string;
  email: string;
  role: string;
  songs: Array<{ id: number; name: string }>; // List of songs
  song: Array<{ id: number; name: string }>; // Optional extra song array
}

export interface UserLoginDTO {
  email: string;
  password: string;
}
