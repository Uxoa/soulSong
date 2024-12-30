import { useState, useEffect } from "react";
import profileService from "../api-services/profiles.services";
import { Profile, ProfileDTO } from "../types";

const useProfile = (id: string) => {
    const [profile, setProfile] = useState<Profile | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                setLoading(true);

                // Validación del ID
                const numericId = parseInt(id, 10);
                if (isNaN(numericId)) {
                    throw new Error("Invalid profile ID");
                }

                // Obtener datos del backend
                const profileDTO: ProfileDTO = await profileService.getProfile(numericId);

                // Mapear datos del DTO a Profile
                const mappedProfile: Profile = {
                    id: profileDTO.id,
                    name: profileDTO.userName || "Unknown Name",
                    avatarUrl: profileDTO.avatarUrl || "/images/default-avatar.png",
                    email: profileDTO.user?.email || "No email available",
                    songs: profileDTO.songs?.map((song) => ({
                        id: song.id,
                        name: song.songEssence?.songName || "Unknown Song",
                        description: song.songEssence?.description || "No description provided",
                        trackId: song.songEssence?.trackId || "No track ID available",
                        danceability: song.songEssence?.danceability || 0,
                        energy: song.songEssence?.energy || 0,
                        tempo: song.songEssence?.tempo || 0,
                        valence: song.songEssence?.valence || 0,
                    })) || [],
                    songEssences: profileDTO.songs
                        ?.filter((song) => song.songEssence) // Filtrar solo canciones con SongEssence
                        .map((song) => ({
                            id: song.songEssence!.id,
                            songName: song.songEssence!.songName,
                            description: song.songEssence!.description || "No description available",
                            trackId: song.songEssence!.trackId,
                            danceability: song.songEssence!.danceability,
                            energy: song.songEssence!.energy,
                            tempo: song.songEssence!.tempo,
                            valence: song.songEssence!.valence,
                        })) || [],
                };

                setProfile(mappedProfile);
            } catch (err) {
                setError(err instanceof Error ? err.message : "An unknown error occurred");
            } finally {
                setLoading(false);
            }
        };

        fetchProfile();
    }, [id]);

    return { profile, loading, error };
};

export default useProfile;
