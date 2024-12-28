import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./UserDetails.css";

const UserDetails: React.FC = () => {
  const navigate = useNavigate(); // Hook para navegar entre páginas
  const [activeTab, setActiveTab] = useState<string>("Pictures"); // Estado para manejar la pestaña activa

  const handleBackClick = () => {
    navigate("/users"); // Regresa a la vista de usuarios
  };

  const renderTabContent = () => {
    switch (activeTab) {
      case "Pictures":
        return <div className="tab-content">Here are Belle's pictures.</div>;
      case "Videos":
        return <div className="tab-content">Here are Belle's videos.</div>;
      case "Belle's Bio":
        return <div className="tab-content">Belle's detailed biography.</div>;
      case "More":
        return <div className="tab-content">More about Belle.</div>;
      default:
        return null;
    }
  };

  return (
      <div className="user-details">
        <header className="details-header">
          <button className="back-button" onClick={handleBackClick}>⬅</button>
          <h2>Belle Benson</h2>
          <span className="menu-icon">⋮</span>
        </header>
        <div className="user-main">
          <div className="profile-picture">
            {/* Aquí iría la imagen del avatar */}
          </div>
          <h3>Belle Benson</h3>
          <p>1.5 km away • 2.7k ❤️</p>
          <p className="user-bio">
            Hello Friends! Love music, cooking, swimming, going out, travelling, etc. Wanna be friends?
          </p>
          <div className="tabs">
            {["Pictures", "Videos", "Belle's Bio", "More"].map((tab) => (
                <button
                    key={tab}
                    className={`tab-button ${activeTab === tab ? "active" : ""}`}
                    onClick={() => setActiveTab(tab)}
                >
                  {tab}
                </button>
            ))}
          </div>
          <div className="tab-content-container">{renderTabContent()}</div>
          <div className="interests">
            <span>🎵 Music</span>
            <span>🍳 Cooking</span>
            <span>🏊 Swimming</span>
            <span>✈️ Travel</span>
          </div>
        </div>
      </div>
  );
};

export default UserDetails;
