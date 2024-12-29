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
                const numericId = parseInt(id, 10);
                if (isNaN(numericId)) throw new Error("Invalid profile ID");

                const profileDTO: ProfileDTO = await profileService.getProfile(numericId);

                // Mapeo explícito de ProfileDTO a Profile
                const mappedProfile: Profile = {
                    id: profileDTO.id,
                    name: profileDTO.userName,
                    avatarUrl: profileDTO.avatar,
                    email: profileDTO.user?.email || "No email available",
                    favoriteSong: profileDTO.songs?.[0]?.songEssence
                        ? {
                            id: profileDTO.songs[0].songEssence.id,
                            songName: profileDTO.songs[0].songEssence.songName,
                            description: profileDTO.songs[0].songEssence.description,
                            trackId: profileDTO.songs[0].songEssence.trackId,
                            danceability: profileDTO.songs[0].songEssence.danceability,
                            energy: profileDTO.songs[0].songEssence.energy,
                            tempo: profileDTO.songs[0].songEssence.tempo,
                            valence: profileDTO.songs[0].songEssence.valence,
                        }
                        : undefined,
                    songs: profileDTO.songs.map((song) => ({
                        id: song.id,
                        name: song.songEssence?.songName || "Unknown Song",
                        description: song.songEssence?.description || "",
                    })),
                    songEssences: profileDTO.songs
                        .filter((song) => song.songEssence)
                        .map((song) => ({
                            id: song.songEssence!.id,
                            songName: song.songEssence!.songName,
                            description: song.songEssence!.description,
                            trackId: song.songEssence!.trackId,
                            danceability: song.songEssence!.danceability,
                            energy: song.songEssence!.energy,
                            tempo: song.songEssence!.tempo,
                            valence: song.songEssence!.valence,
                        })),
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
