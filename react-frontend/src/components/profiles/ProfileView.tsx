import { useParams, useNavigate } from "react-router-dom";
import "./ProfileView.css";
import useProfile from "../../hooks/useProfile";

const ProfileView = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { profile, loading, error } = useProfile(id!);

  if (loading) return <p>Loading profile...</p>;
  if (error) return <p>Error loading profile: {error}</p>;
  if (!profile) return <p>Profile not found</p>;

  return (
      <div className="profile-view">
        <header className="profile-header">
          <button className="back-button" onClick={() => navigate("/profiles")}>
            ⬅ Back
          </button>
          <h1>{profile.name}'s Profile</h1>
        </header>
        <div className="profile-card">
          <div className="profile-picture-container">
            <img
                src={profile.imageUrl}
                alt={`${profile.name}'s profile`}
                className="profile-picture"
            />
          </div>
          <h2>{profile.name}</h2>
          <p>Favorite Song: <span>{profile.favoriteSong?.name || "None"}</span></p>
          <p>Email: <span>{profile.email}</span></p>
          <p>Bio: <span>{profile.bio || "No bio available"}</span></p>
          {profile.songEssences && profile.songEssences.length > 0 && (
              <div className="song-essences">
                <h3>Song Essences</h3>
                <ul>
                  {profile.songEssences.map((essence) => (
                      <li key={essence.id}>
                        <h4>{essence.name}</h4>
                        <p>{essence.description}</p>
                      </li>
                  ))}
                </ul>
              </div>
          )}
        </div>
      </div>
  );
};

export default ProfileView;
