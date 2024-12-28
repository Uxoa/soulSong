import React from "react";
import useProfiles from "../../hooks/useProfiles"; // Hook para obtener perfiles
import ProfileCard from "./ProfileCard";
import "./ProfileManager.css";

const ProfileManager: React.FC = () => {
    const { profiles, loading, error } = useProfiles();

    if (loading) return <p>Loading profiles...</p>;
    if (error) return <p>Error loading profiles: {error}</p>;

    return (
        <div className="user-manager-container">
            <header className="user-manager-header">
                <h2>Explore Profiles</h2>
                <div className="header-icons">
                    <span className="icon">🔍</span>
                    <span className="icon">🔔</span>
                    <span className="icon">⋮</span>
                </div>
            </header>
            <div className="user-manager-grid">
                {profiles.map((profile) => (
                    <ProfileCard
                        key={profile.id}
                        id={profile.id}
                        name={profile.name}
                        imageUrl={profile.imageUrl}
                        favoriteSong={profile.favoriteSong?.name}
                    />
                ))}
            </div>
        </div>
    );
};

export default ProfileManager;
