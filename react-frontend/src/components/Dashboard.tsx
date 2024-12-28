import React from "react";
import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

const Dashboard: React.FC = () => {
    const navigate = useNavigate();

    const handleNavigate = (path: string) => {
        navigate(path);
    };

    return (
        <div className="dashboard">
            <header className="dashboard-header">
                <h1>Welcome to SoulSong</h1>
                <p>The only & one no gender dating app!</p>
            </header>
            <div className="dashboard-cards">
                <div className="dashboard-card" onClick={() => handleNavigate("/profiles")}>
                    <div className="card01"> </div>
                    <h3>Profiles</h3>
                    <p>Explore and manage user profiles.</p>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
