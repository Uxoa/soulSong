import React from 'react';

interface SongDetailProps {
  song: any; // Define un tipo explícito si tienes un DTO
}

const SongDetail: React.FC<SongDetailProps> = ({ song }) => {
  return (
      <div>
        <h3>{song.title}</h3>
        <p>Artista: {song.artist}</p>
        <p>Género: {song.genre}</p>
      </div>
  );
};

export default SongDetail;
