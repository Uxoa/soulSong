import { useState, useEffect } from 'react';
import { getSongs } from '../api-services/songs.services';

const useSongs = () => {
  const [songs, setSongs] = useState<any[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchSongs = async () => {
      try {
        const data = await getSongs();
        setSongs(data);
      } catch (error) {
        console.error('Error fetching songs:', error);
      } finally {
        setLoading(false);
      }
    };
    fetchSongs();
  }, []);

  return { songs, loading };
};

export default useSongs;
