import { useState, useEffect } from "react";
import { profileService } from "../api-services/profiles.services";
import { Profile } from "../types";

const useProfiles = () => {
  const [profiles, setProfiles] = useState<Profile[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProfiles = async () => {
      try {
        setLoading(true);
        const response = await profileService.getProfiles(); // Aquí usamos getProfiles desde profileService
        setProfiles(response.data);
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
