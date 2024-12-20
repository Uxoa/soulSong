import { useState, useEffect } from "react";
import { getProfiles } from "../api-services/profiles.services";
import { ProfileDTO } from "../types";

interface Profile {
  id: string;
  imageUrl: string;
  name: string;
  favoriteSong: {
    name: string;
  };
}

const useProfiles = () => {
  const [profiles, setProfiles] = useState<Profile[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProfiles = async () => {
      try {
        const profiles: ProfileDTO[] = await getProfiles();
        setProfiles(profiles.map((profile) => ({
          id: profile.id.toString(),
          imageUrl: profile.imageUrl,
          name: profile.name,
          favoriteSong: {
            name: profile.favoriteSongName,
          },
        })));
      } catch (err) {
        setError(err instanceof Error ? err.message : "An unknown error occurred");
      } finally {
        setLoading(false);
      }
    };

    fetchProfiles();
  }, []);

  return { profiles, loading, error };
};

export default useProfiles;