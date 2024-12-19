import { useState, useEffect } from 'react';
import { getSongs, SongDTO } from '../../api-services/songs.services';

const SongManager = () => {
    const [songs, setSongs] = useState<SongDTO[]>([]);

    useEffect(() => {
        loadSongs();
    }, []);

    const loadSongs = async () => {
        const data = await getSongs();
        setSongs(data);
    };

    return (
        <div>
            <h1>Listado de Canciones</h1>
            <ul>
                {songs.map((song) => (
                    <li key={song.id}>
                        {song.title} - {song.artist} ({song.genre})
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default SongManager;
