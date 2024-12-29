import React from "react";
import { useNavigate } from "react-router-dom";
import "./ProfileCard.css";
import { SongEssence } from "../../types";

type ProfileCardProps = {
    id: number;
    userName: string;
    avatarUrl: string;
    favoriteSong?: SongEssence;
};

const ProfileCard: React.FC<ProfileCardProps> = ({ id, userName, favoriteSong }) => {
    const navigate = useNavigate();

    const handleViewProfile = () => {
        navigate(`/profiles/${id}`);
    };

    return (
        <div className="profile-card">
            <div className="profile-status online"></div>
            <img
                src="/images/avatar01.png"
                alt={`${userName}'s profile`}
                className="profile-picture"
            />
            <h3 className="profile-name">{userName}</h3>
            <p className="profile-song">
                {favoriteSong?.songName ? `🎵 ${favoriteSong.songName}` : "No favorite song"}
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
