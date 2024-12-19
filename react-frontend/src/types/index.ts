export interface User {
  id: string;
  name: string;
  email: string;
  favoriteSongs: string[];
}

export interface ProfileDTO {
  id: number; // ID del perfil
  bio: string; // Biografía del perfil
  favoriteGenres: string[]; // Lista de géneros favoritos
  userId: number; // ID del usuario asociado
}


export interface Profile {
  id: string; // ID convertido a string
  userId: string; // ID del usuario convertido a string
  bio: string; // Biografía
}
