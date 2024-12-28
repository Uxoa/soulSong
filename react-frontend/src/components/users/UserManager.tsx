import React from "react";
import { useNavigate } from "react-router-dom";
import "./UserManager.css";

const UserManager: React.FC = () => {
    const users = [
        { id: 1, name: "Belle Benson", age: 28, distance: "1.5 km", likes: 85, online: true },
        { id: 2, name: "Ruby Diaz", age: 33, distance: "1.2 km", likes: 81, online: false },
        { id: 3, name: "Myley Corbyn", age: 23, distance: "1.5 km", likes: 35, online: true },
        { id: 4, name: "Tony Z", age: 25, distance: "2 km", likes: 29, online: false },
    ];

    const navigate = useNavigate(); // Hook para navegación

    const handleCardClick = (userId: number) => {
        navigate(`/users/${userId}`); // Navega a la ruta de detalles del usuario
    };

    return (
        <div className="user-manager-container">
            <header className="user-manager-header">
                <h2>Add Your Story</h2>
                <div className="story-circle">+</div>
                <div className="header-icons">
                    <span className="icon">🔍</span>
                    <span className="icon">🔔</span>
                    <span className="icon">⋮</span>
                </div>
            </header>
            <div className="user-manager-filters">
                <button>All</button>
                <button>Online</button>
                <button>New Daters</button>
                <button>Liked You</button>
            </div>
            <div className="user-manager-grid">
                {users.map((user) => (
                    <div
                        className="user-card"
                        key={user.id}
                        onClick={() => handleCardClick(user.id)} // Maneja el clic en la tarjeta
                    >
                        <div className={`user-status ${user.online ? "online" : "offline"}`}></div>
                        <h3>{user.name}, {user.age}</h3>
                        <p>{user.distance} away</p>
                        <span className="user-likes">{user.likes} ❤️</span>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default UserManager;
