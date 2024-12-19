import { useState, useEffect } from 'react';
import { getUsers, createUser, updateUser, deleteUser, UserDTO } from '../../api-services/users.services';
import UserForm from './UserForm';
import './UserManager.css';

const UserManager = () => {
    const [users, setUsers] = useState<UserDTO[]>([]);
    const [selectedUser, setSelectedUser] = useState<UserDTO | undefined>();
    const [showForm, setShowForm] = useState(false);

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        const data = await getUsers();
        setUsers(data);
    };

    const handleCreate = () => {
        setSelectedUser(undefined);
        setShowForm(true);
    };

    const handleEdit = (user: UserDTO) => {
        setSelectedUser(user);
        setShowForm(true);
    };

    const handleDelete = async (id: number) => {
        await deleteUser(id);
        loadUsers();
    };

    const handleSave = async (user: UserDTO) => {
        if (selectedUser?.id) {
            await updateUser(selectedUser.id, user);
        } else {
            await createUser(user);
        }
        setShowForm(false);
        loadUsers();
    };

    return (
        <div className="user-manager">
            <h1>Gestión de Usuarios</h1>
            <button className="button create-button" onClick={handleCreate}>
                Crear Usuario
            </button>
            <div className="user-grid">
                {users.map((user) => (
                    <div className="user-card" key={user.id}>
                        <h3>{user.name}</h3>
                        <p>{user.email}</p>
                        <div className="user-card-buttons">
                            <button className="button edit-button" onClick={() => handleEdit(user)}>
                                Editar
                            </button>
                            <button className="button delete-button" onClick={() => handleDelete(user.id!)}>
                                Eliminar
                            </button>
                        </div>
                    </div>
                ))}
            </div>
            {showForm && (
                <div className="form-overlay">
                    <div className="form-container">
                        <UserForm
                            user={selectedUser}
                            onSave={handleSave}
                            onCancel={() => setShowForm(false)}
                        />
                    </div>
                </div>
            )}
        </div>
    );
};

export default UserManager;
