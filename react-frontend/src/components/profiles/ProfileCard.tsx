import React from "react";
import { useNavigate } from "react-router-dom";
import "./ProfileManager.css";

type ProfileCardProps = {
    id: string;
    name: string;
    imageUrl: string;
    favoriteSong?: string;
};

const ProfileCard: React.FC<ProfileCardProps> = ({ id, name, imageUrl, favoriteSong }) => {
    const navigate = useNavigate();

    const handleViewProfile = () => {
        navigate(`/profiles/${id}`);
    };

    return (
        <div className="user-card">
            <div className="user-status online"></div>
            <img
                src={imageUrl}
                alt={`${name}'s profile`}
                className="profile-image"
            />
            <h3>{name}</h3>
            <p>{favoriteSong ? `🎵 ${favoriteSong}` : "No favorite song"}</p>
            <div className="card-actions">
                <button className="edit-button" onClick={handleViewProfile}>
                    View
                </button>
            </div>
        </div>
    );
};

export default ProfileCard;
