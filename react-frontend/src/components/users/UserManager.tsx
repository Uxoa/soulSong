import { useState, useEffect } from "react";
import {
    getUsers,
    createUser,
    updateUser,
    deleteUser,
    UserDTO,
} from "../../api-services/users.services";
import UserForm from "./UserForm";
import "./UserManager.css";

const UserManager = () => {
    const [users, setUsers] = useState<UserDTO[]>([]);
    const [selectedUser, setSelectedUser] = useState<UserDTO | undefined>();
    const [showForm, setShowForm] = useState(false);

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        const userList = await getUsers();
        setUsers(userList);
    };

    const handleCreate = () => {
        setSelectedUser(undefined);
        setShowForm(true);
    };

    const handleEdit = (user: UserDTO) => {
        setSelectedUser(user);
        setShowForm(true);
    };

    const handleDelete = async (userId: number) => {
        await deleteUser(userId);
        loadUsers();
    };

    return (
        <div className="user-manager">
            <header className="user-manager-header">
                <h1>Manage Users</h1>
                <button className="create-button" onClick={handleCreate}>
                    + Create User
                </button>
            </header>
            {showForm ? (
                <UserForm
                    user={selectedUser}
                    onClose={() => setShowForm(false)}
                    onSave={() => {
                        setShowForm(false);
                        loadUsers();
                    }}
                />
            ) : (
                <div className="user-grid">
                    {users.map((user) => (
                        <div className="user-card" key={user.id}>
                            <h3>{user.name}</h3>
                            <p>Email: {user.email}</p>
                            <p>Role: {user.role}</p>
                            <div className="card-actions">
                                <button
                                    className="edit-button"
                                    onClick={() => handleEdit(user)}
                                >
                                    Edit
                                </button>
                                <button
                                    className="delete-button"
                                    onClick={() => handleDelete(user.id)}
                                >
                                    Delete
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default UserManager;
