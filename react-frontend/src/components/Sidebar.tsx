import { NavLink } from "react-router-dom";
import "./Sidebar.css";

const Sidebar = () => {
    return (
        <div className="sidebar">
            <h2 className="sidebar-title">SoulSong</h2>
            <nav>
                <ul className="sidebar-links">
                    <li>
                        <NavLink
                            to="/dashboard"
                            className={({ isActive }) =>
                                isActive ? "link active" : "link"
                            }
                        >
                            Dashboard
                        </NavLink>
                    </li>
                    <li>
                        <NavLink
                            to="/users"
                            className={({ isActive }) =>
                                isActive ? "link active" : "link"
                            }
                        >
                            Usuarios
                        </NavLink>
                    </li>
                    <li>
                        <NavLink
                            to="/profiles"
                            className={({ isActive }) =>
                                isActive ? "link active" : "link"
                            }
                        >
                            Perfiles
                        </NavLink>
                    </li>
                    <li>
                        <NavLink
                            to="/songs"
                            className={({ isActive }) =>
                                isActive ? "link active" : "link"
                            }
                        >
                            Canciones
                        </NavLink>
                    </li>
                </ul>
            </nav>
        </div>
    );
};

export default Sidebar;
