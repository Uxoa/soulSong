import React from "react";
import { User } from "../../types";

interface UserTableProps {
    users: User[];
    onEdit: (id: string) => void;
    onDelete: (id: string) => void;
}

const UserTable: React.FC<UserTableProps> = ({ users, onEdit, onDelete }) => {
    // Verificación adicional
    if (!Array.isArray(users)) {
        return <p className="text-red-500">Error: users is not an array</p>;
    }

    return (
        <div className="bg-white shadow rounded-lg p-4">
            <table className="w-full table-auto">
                <thead>
                <tr>
                    <th className="border px-4 py-2">First Name</th>
                    <th className="border px-4 py-2">Last Name</th>
                    <th className="border px-4 py-2">Email</th>
                    <th className="border px-4 py-2">Phone</th>
                    <th className="border px-4 py-2">Actions</th>
                </tr>
                </thead>
                <tbody>
                {users.map((user) => (
                    <tr key={user.id}>
                        <td className="border px-4 py-2">{user.firstName}</td>
                        <td className="border px-4 py-2">{user.lastName}</td>
                        <td className="border px-4 py-2">{user.email}</td>
                        <td className="border px-4 py-2">{user.phoneNumber}</td>
                        <td className="border px-4 py-2">
                            <button
                                onClick={() => onEdit(user.id)}
                                className="text-blue-500 mr-2"
                            >
                                Edit
                            </button>
                            <button
                                onClick={() => onDelete(user.id)}
                                className="text-red-500"
                            >
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default UserTable;
