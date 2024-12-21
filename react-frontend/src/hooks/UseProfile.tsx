import { useState, useEffect } from "react";

interface SongEssence {
  id: string;
  name: string;
  description: string;
}

interface Profile {
  id: string;
  name: string;
  imageUrl: string;
  favoriteSong?: { name: string };
  email: string;
  bio: string;
  songEssences?: SongEssence[];
}

const useProfile = (id: string) => {
  const [profile, setProfile] = useState<Profile | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    (async () => {
      try {
        const response = await fetch(`/profiles/${id}`);
        const contentType = response.headers.get("content-type");
        console.log("Profile response content type:", contentType);
        if (!response.ok) {
          const errorText = await response.text();
          console.error("Failed to fetch profile:", errorText);
          setError("Failed to fetch profile");
          return;
        }
        if (!contentType || !contentType.includes("application/json")) {
          const errorText = await response.text();
          console.error("Received non-JSON response:", errorText);
          setError("Received non-JSON response");
          return;
        }
        const data: Profile = await response.json();

        if (data.favoriteSong) {
          const songEssencesResponse = await fetch(`/api/songs/${data.favoriteSong.name}/essences`);
          const songEssencesContentType = songEssencesResponse.headers.get("content-type");
          console.log("Song essences response content type:", songEssencesContentType);
          if (!songEssencesResponse.ok) {
            const errorText = await songEssencesResponse.text();
            console.error("Failed to fetch song essences:", errorText);
            setError("Failed to fetch song essences");
            return;
          }
          if (!songEssencesContentType || !songEssencesContentType.includes("application/json")) {
            const errorText = await songEssencesResponse.text();
            console.error("Received non-JSON response for song essences:", errorText);
            setError("Received non-JSON response for song essences");
            return;
          }
          const songEssences: SongEssence[] = await songEssencesResponse.json();
          data.songEssences = songEssences;
        }

        setProfile(data);
      } catch (err) {
        setError(err instanceof Error ? err.message : "An unknown error occurred");
      } finally {
        setLoading(false);
      }
    })();
  }, [id]);

  return { profile, loading, error };
};

export default useProfile;