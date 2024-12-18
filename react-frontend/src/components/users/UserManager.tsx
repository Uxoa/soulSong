import React, { useState } from "react";
import { useUsers } from "../../hooks/useUser";
import UserTable from "./UserTable";
import UserForm from "./UserForm";
import { User } from "../../types";

const UserManager: React.FC = () => {
    const { users, addUser, updateUser, deleteUser } = useUsers();
    const [showForm, setShowForm] = useState<boolean>(false);
    const [selectedUserId, setSelectedUserId] = useState<string | null>(null);
    const [initialData, setInitialData] = useState<Partial<User> | null>(null);

    const handleShowForm = (userId?: string) => {
        setSelectedUserId(userId || null);
        setShowForm(true);

        if (userId) {
            const user = users.find((u) => u.id === userId);
            if (user) {
                setInitialData({
                    firstName: user.firstName,
                    lastName: user.lastName,
                    email: user.email,
                    phoneNumber: user.phoneNumber,
                });
            }
        } else {
            setInitialData(null); // Formulario vacío para agregar usuario
        }
    };

    const handleCloseForm = () => {
        setShowForm(false);
        setSelectedUserId(null);
        setInitialData(null);
    };

    return (
        <div className="p-6 max-w-6xl mx-auto">
            <h1 className="text-3xl font-bold mb-6">User Management</h1>

            {/* Botón para Agregar Usuario */}
            <button
                onClick={() => handleShowForm()}
                className="bg-blue-500 text-white px-4 py-2 rounded mb-4"
            >
                Add User
            </button>

            {/* Tabla de Usuarios */}
            <UserTable
                users={users}
                onEdit={handleShowForm}
                onDelete={deleteUser}
            />

            {/* Formulario para Agregar/Editar Usuarios */}
            {showForm && (
                <div className="mt-6 p-4 bg-gray-100 rounded-lg">
                    <UserForm
                        initialData={initialData || undefined} // Pasa valores iniciales al editar
                        onSubmit={(formData) => {
                            if (selectedUserId) {
                                updateUser(selectedUserId, formData); // Actualizar usuario
                            } else {
                                addUser(formData); // Agregar nuevo usuario
                            }
                            handleCloseForm();
                        }}
                        onCancel={handleCloseForm}
                    />
                </div>
            )}
        </div>
    );
};

export default UserManager;
