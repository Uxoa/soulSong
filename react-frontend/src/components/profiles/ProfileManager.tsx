import React, { useEffect, useState } from "react";
import ProfileCard from "./ProfileCard";
import profileService from "../../api-services/profiles.services";
import { Profile } from "../../types";
import "./ProfileManager.css";

const ProfileManager: React.FC = () => {
    const [profiles, setProfiles] = useState<Profile[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchProfiles = async () => {
            try {
                const response = await profileService.getProfiles();
                const mappedProfiles: Profile[] = response.map((profile: any) => ({
                    id: profile.id,
                    name: profile.name,
                    avatarUrl: profile.avatarUrl || "/images/avatar03.png", // Default avatar
                    email: profile.email || "No email provided", // Default email
                    songs: profile.songs.map((song: any) => ({
                        id: song.id,
                        name: song.name || "Unknown Title", // Default song name
                    })),
                    song: [], // Provide an empty array for `song` if not available
                }));
                setProfiles(mappedProfiles);
            } catch (err) {
                setError(err instanceof Error ? err.message : "An error occurred");
            } finally {
                setLoading(false);
            }
        };

        fetchProfiles();
    }, []);

    if (loading) return <p>Loading profiles...</p>;
    if (error) return <p>Error loading profiles: {error}</p>;

    return (
        <div className="profile-manager">
            <h1>User Profiles</h1>
            <div className="profile-cards">
                {profiles.map((profile) => (
                    <ProfileCard
                        key={profile.id}
                        id={profile.id}
                        userName={profile.name}
                        avatarUrl={profile.avatarUrl} // Pass avatarUrl to ProfileCard
                        songs={profile.songs}
                    />
                ))}
            </div>
        </div>
    );
};

export default ProfileManager;
