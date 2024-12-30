import React from "react";
import { useNavigate } from "react-router-dom";
import "./ProfileCard.css";

type ProfileCardProps = {
    id: number;
    userName: string;
    avatarUrl: string; // Avatar URL provided by the backend
    songs?: { id: number; name: string }[];
};

const ProfileCard: React.FC<ProfileCardProps> = ({ id, userName, avatarUrl, songs }) => {
    const navigate = useNavigate();

    const handleViewProfile = () => {
        navigate(`/profiles/${id}`);
    };

    return (
        <div className="profile-card">
            <div className="profile-status online"></div>
            <img
                src={avatarUrl || "/images/avatar01.png"} // Use avatarUrl directly
                alt={`${userName}'s profile`}
                className="profile-picture"
            />
            <h3 className="profile-name">{userName}</h3>
            <p className="profile-song">
                {songs?.[0]?.name || "No songs available"}
            </p>
            <div className="card-actions">
                <button className="view-button" onClick={handleViewProfile}>
                    View Profile
                </button>
            </div>
        </div>
    );
};

export default ProfileCard;

