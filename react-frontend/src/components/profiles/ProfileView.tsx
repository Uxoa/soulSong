import { useParams } from "react-router-dom";
import "./ProfileView.css";
import useProfile from "../../hooks/UseProfile";

const ProfileView = () => {
  const { id } = useParams();
  const { profile, loading, error } = useProfile(id!);

  if (loading) return <p>Loading profile...</p>;
  if (error) return <p>Error loading profile: {error}</p>;
  if (!profile) return <p>Profile not found</p>;

  return (
    <div className="profile-view">
      <div className="profile-card">
        <img
          src={profile.imageUrl}
          alt={`${profile.name}'s profile`}
          className="profile-image"
        />
        <h2>{profile.name}</h2>
        <p>Favorite Song: {profile.favoriteSong?.name || "None"}</p>
        <p>Email: {profile.email}</p>
        <p>Bio: {profile.bio}</p>
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