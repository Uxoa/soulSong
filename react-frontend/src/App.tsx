import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import Dashboard from "./components/Dashboard";
import UserManager from "./components/users/UserManager";
import ProfileManager from "./components/profiles/ProfileManager";
import SongManager from "./components/songs/SongManager";
import ProfileView from "./components/profiles/ProfileView";

const App = () => {
  return (
    <Router>
      <Navbar />
      <main className="main-content">
        <Routes>
          <Route path="/" element={<Navigate to="/dashboard" />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/users" element={<UserManager />} />
            <Route path="/profiles" element={<ProfileManager />} />
            <Route path="/profiles/:id" element={<ProfileView />} />
          <Route path="/songs" element={<SongManager />} />
        </Routes>
      </main>
    </Router>
  );
};

export default App;