import React, { useState } from 'react';
import { createSong, updateSong, SongDTO } from '../../api-services/songs.services';

interface SongFormProps {
  song?: SongDTO; // Tipo explícito para las canciones
  onSave: () => void;
}

const SongForm: React.FC<SongFormProps> = ({ song, onSave }) => {
  const [formData, setFormData] = useState<Omit<SongDTO, 'id'>>(
      song ? { title: song.title, artist: song.artist, genre: song.genre } : { title: '', artist: '', genre: '' }
  );

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (song?.id) {
      await updateSong(song.id, formData);
    } else {
      await createSong(formData);
    }
    onSave();
  };

  return (
      <form onSubmit={handleSubmit}>
        <div>
          <label>Título:</label>
          <input
              type="text"
              name="title"
              value={formData.title}
              onChange={handleChange}
              required
          />
        </div>
        <div>
          <label>Artista:</label>
          <input
              type="text"
              name="artist"
              value={formData.artist}
              onChange={handleChange}
              required
          />
        </div>
        <div>
          <label>Género:</label>
          <input
              type="text"
              name="genre"
              value={formData.genre}
              onChange={handleChange}
              required
          />
        </div>
        <button type="submit">{song ? 'Actualizar' : 'Crear'}</button>
      </form>
  );
};

export default SongForm;
