import React from 'react';

interface UserListProps {
    users: any[]; // Define un tipo explícito si tienes un DTO
    onEdit: (user: any) => void;
    onDelete: (id: number) => void;
}

const UserList: React.FC<UserListProps> = ({ users, onEdit, onDelete }) => {
    return (
        <ul>
            {users.map((user) => (
                <li key={user.id}>
                    {user.name} - {user.email}
                    <button onClick={() => onEdit(user)}>Editar</button>
                    <button onClick={() => onDelete(user.id)}>Eliminar</button>
                </li>
            ))}
        </ul>
    );
};

export default UserList;
