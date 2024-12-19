import { Link } from "react-router-dom";
import "./Dashboard.css";

const Dashboard = () => {
    return (
        <div className="dashboard">
            <h1>Bienvenido a SoulSong</h1>
            <div className="dashboard-cards">
                <Link to="/users" className="dashboard-card">
                    <h3>Usuarios</h3>
                    <p>Administra los usuarios registrados en la aplicación.</p>
                </Link>
                <Link to="/profiles" className="dashboard-card">
                    <h3>Perfiles</h3>
                    <p>Explora y administra los perfiles de los usuarios.</p>
                </Link>
                <Link to="/songs" className="dashboard-card">
                    <h3>Canciones</h3>
                    <p>Descubre y gestiona las canciones favoritas de los usuarios.</p>
                </Link>
            </div>
        </div>
    );
};

export default Dashboard;
