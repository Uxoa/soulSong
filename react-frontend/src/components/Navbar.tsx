import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import { Menu, X } from "lucide-react";
import "./Navbar.css";

const Navbar: React.FC = () => {
    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => setIsOpen(!isOpen);

    return (
        <header className="navbar">
            <div className="navbar-logo">SoulSong</div>
            <button className="burger-menu" onClick={toggleMenu}>
                {isOpen ? <X size={24} /> : <Menu size={24} />}
            </button>
            <nav className={`navbar-links ${isOpen ? "open" : ""}`}>
                <ul>
                    <li>
                        <NavLink
                            to="/dashboard"
                            className={({ isActive }) => (isActive ? "link active" : "link")}
                        >
                            Dashboard
                        </NavLink>
                    </li>
                    <li>
                        <NavLink
                            to="/users"
                            className={({ isActive }) => (isActive ? "link active" : "link")}
                        >
                            Usuarios
                        </NavLink>
                    </li>
                    <li>
                        <NavLink
                            to="/profiles"
                            className={({ isActive }) => (isActive ? "link active" : "link")}
                        >
                            Perfiles
                        </NavLink>
                    </li>
                    <li>
                        <NavLink
                            to="/songs"
                            className={({ isActive }) => (isActive ? "link active" : "link")}
                        >
                            Canciones
                        </NavLink>
                    </li>
                </ul>
            </nav>
        </header>
    );
};

export default Navbar;
