import React from 'react';
import './Banner.css'; // Importa el archivo CSS para los estilos

const Banner: React.FC = () => {
  return (
    <div className="banner">
      <div className="banner-content">
        <h1>Welcome to the Dashboard</h1>
        <p>Your central hub for all activities</p>
      </div>
    </div>
  );
};

export default Banner;