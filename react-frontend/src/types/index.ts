export interface User {
  id: string;
  name: string;
  email: string;
  favoriteSongs: string[];
  role: never;

}

export interface ProfileDTO {
  favoriteSongName: string;
    imageUrl: string;
  name: string;
  id: number; // ID del perfil
  bio: string; // Biografía del perfil
  favoriteGenres: string[]; // Lista de géneros favoritos
  userId: number; // ID del usuario asociado
}


export interface Profile {
  id: string; // ID convertido a string
  data: string;
  bio: string; // Biografía
  userId: string; // ID del usuario convertido a string

}
