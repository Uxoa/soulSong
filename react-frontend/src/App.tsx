import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./components/Dashboard";
import ProfileManager from "./components/profiles/ProfileManager"; // Para listar perfiles
import ProfileView from "./components/profiles/ProfileView"; // Para detalles de un perfil
import UserManager from "./components/users/UserManager"; // Administra usuarios (si sigue siendo necesario)
import UserDetails from "./components/users/UserDetail"; // Detalles de usuarios (si aplica)

const App = () => {
    return (
        <Router>
            <Routes>
                {/* Ruta principal para el dashboard */}
                <Route path="/dashboard" element={<Dashboard />} />

                {/* Rutas para perfiles */}
                <Route path="/profiles" element={<ProfileManager />} />
                <Route path="/profiles/:id" element={<ProfileView />} />

                {/* Rutas para usuarios (si se requieren en backend o administración) */}
                <Route path="/users" element={<UserManager />} />
                <Route path="/users/:id" element={<UserDetails />} />
            </Routes>
        </Router>
    );
};

export default App;
