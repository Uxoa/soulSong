import React from "react";
import "./Dashboard.css";

const Dashboard: React.FC = () => {
    return (
        <div className="dashboard">
            <header className="dashboard-header">
                <h1>Welcome to SoulSong</h1>
                <p>The only & one no gender dating app!</p>
            </header>
            <div className="dashboard-cards">
                {/* Card 1 */}
                <div className="dashboard-card">
                    <div className="card-icon">👥</div>
                    <h3>Users</h3>
                    <p>Manage all registered users in the application.</p>
                </div>
                {/* Card 2 */}
                <div className="dashboard-card">
                    <div className="card-icon">📂</div>
                    <h3>Profiles</h3>
                    <p>Manage profiles created by users.</p>
                </div>
                {/* Card 3 */}
                <div className="dashboard-card">
                    <div className="card-icon">🎵</div>
                    <h3>Songs</h3>
                    <p>Explore and organize the song library.</p>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
