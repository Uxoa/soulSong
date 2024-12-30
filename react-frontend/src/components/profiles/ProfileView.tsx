import { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./ProfileView.css";
import useProfile from "../../hooks/useProfile";
import { analyzeSong } from "../../api-services/songEssence.service";
import { SongEssence } from "../../types";

const ProfileView = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const { profile, loading, error } = useProfile(id!);

    const [analyzedEssence, setAnalyzedEssence] = useState<SongEssence | null>(null);
    const [description, setDescription] = useState<string | null>(null);
    const [analyzing, setAnalyzing] = useState(false);

    const handleAnalyze = async () => {
        if (!profile?.song?.trackId) {
            console.error("No track ID available for analysis.");
            return;
        }

        try {
            setAnalyzing(true);
            const data = await analyzeSong(profile.song.trackId);
            setAnalyzedEssence(data.songEssence);
            setDescription(data.description);
        } catch (err) {
            console.error("Error analyzing song:", err);
        } finally {
            setAnalyzing(false);
        }
    };


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
                        src={profile.avatarUrl || "/images/avatar01.png"}
                        alt={`${profile.name}'s profile`}
                        className="profile-picture"
                    />
                </div>
                <h2>{profile.name}</h2>
                <p>
                    Favorite Song:{" "}
                    <span>{profile.song?.songName || "No favorite song"}</span>
                </p>
                <p>Email: <span>{profile.email || "No email provided"}</span></p>
                {profile.songEssences && profile.songEssences.length > 0 && (
                    <div className="song-essences">
                        <h3>Song Essences</h3>
                        <ul>
                            {profile.songEssences.map((essence: SongEssence) => (
                                <li key={essence.id}>
                                    <h4>{essence.songName}</h4>
                                    <p><strong>Tempo:</strong> {essence.tempo}</p>
                                    <p><strong>Danceability:</strong> {essence.danceability}</p>
                                    <p><strong>Energy:</strong> {essence.energy}</p>
                                    <p><strong>Valence:</strong> {essence.valence}</p>
                                    <p><strong>Description:</strong> {essence.description || "No description provided"}</p>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}


                {/* Botón para analizar la canción favorita */}
                <div className="analyze-section">
                    <h3>Analyze Song</h3>
                    <button onClick={handleAnalyze} disabled={analyzing || !profile.song?.trackId}>
                        {analyzing ? "Analyzing..." : "Analyze Song"}
                    </button>
                </div>

                {/* Mostrar los resultados analizados */}
                {analyzedEssence && (
                    <div className="analyzed-essence">
                        <h3>Analyzed Song Essence</h3>
                        <p><strong>Tempo:</strong> {analyzedEssence.tempo}</p>
                        <p><strong>Danceability:</strong> {analyzedEssence.danceability}</p>
                        <p><strong>Energy:</strong> {analyzedEssence.energy}</p>
                        <p><strong>Valence:</strong> {analyzedEssence.valence}</p>
                        <h3>Description</h3>
                        <p>{description}</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default ProfileView;
