import React from 'react';

interface SongListProps {
  songs: any[]; // Define un tipo explícito si tienes un DTO
  onDelete: (id: number) => void;
}

const SongList: React.FC<SongListProps> = ({ songs, onDelete }) => {
  return (
      <ul>
        {songs.map((song) => (
            <li key={song.id}>
              {song.title} - {song.artist}
              <button onClick={() => onDelete(song.id)}>Eliminar</button>
            </li>
        ))}
      </ul>
  );
};

export default SongList;
