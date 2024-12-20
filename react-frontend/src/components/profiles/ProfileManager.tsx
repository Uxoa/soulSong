import { useNavigate } from "react-router-dom";
import useProfiles from "../../hooks/useProfiles";
import "./ProfileManager.css"; // Import the CSS file for styling

const ProfileManager = () => {
  const { profiles, loading, error } = useProfiles();
  const navigate = useNavigate();

  const handleViewProfile = (id: string) => {
    navigate(`/profiles/${id}`);
  };

  if (loading) return <p>Loading profiles...</p>;
  if (error) return <p>Error loading profiles: {error}</p>;

  return (
    <div className="profile-manager">
      <h1>User Profiles</h1>
      <div className="profile-cards">
        {profiles.map((profile) => (
          <div key={profile.id} className="profile-card">
            <img
              src={profile.imageUrl}
              alt={`${profile.name}'s profile`}
              className="profile-image"
            />
            <h2>{profile.name}</h2>
            <p>Favorite Song: {profile.favoriteSong?.name || "None"}</p>
            <div className="profile-buttons">
              <button
                onClick={() => handleViewProfile(profile.id)}
                className="btn-profile"
              >
                View Profile
              </button>
              <button
                onClick={() => navigate(`/profiles/${profile.id}/similar`)}
                className="btn-similar"
              >
                Find Similar
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProfileManager;