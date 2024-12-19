import { useState, useEffect } from "react";
import { getProfiles } from "../../api-services/profiles.services";
import { getUsers } from "../../api-services/users.services";
import { Profile, User, ProfileDTO } from "../../types";
import "./ProfileManager.css";

const ProfileManager = () => {
    const [profiles, setProfiles] = useState<Profile[]>([]);
    const [users, setUsers] = useState<User[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const loadData = async () => {
            try {
                const profilesData: ProfileDTO[] = await getProfiles();
                const usersData: User[] = await getUsers();

                const transformedProfiles: Profile[] = profilesData
                    .filter(
                        (profile): profile is Required<ProfileDTO> =>
                            profile.id !== undefined && profile.userId !== undefined
                    )
                    .map((profile) => ({
                        id: profile.id.toString(),
                        userId: profile.userId.toString(),
                        bio: profile.bio || "Sin biografía",
                    }));

                setProfiles(transformedProfiles);
                setUsers(usersData);
            } catch (error) {
                console.error("Error loading data:", error);
            } finally {
                setLoading(false);
            }
        };

        loadData();
    }, []);

    const findUserById = (userId: string): User | undefined => {
        return users.find((user) => user.id === userId);
    };

    if (loading) {
        return <div className="loading">Cargando perfiles...</div>;
    }

    if (profiles.length === 0) {
        return <div className="loading">No hay perfiles disponibles.</div>;
    }

    return (
        <div className="profile-manager">
            <h1 className="title">Gestión de Perfiles</h1>
            <div className="profile-grid">
                {profiles.map((profile) => {
                    const user = findUserById(profile.userId);
                    return (
                        <div className="profile-card" key={profile.id}>
                            <div className="profile-header">
                                <h3>{user?.name || "Usuario no encontrado"}</h3>
                                <p className="profile-email">{user?.email || ""}</p>
                            </div>
                            <p className="profile-bio">{profile.bio}</p>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default ProfileManager;
