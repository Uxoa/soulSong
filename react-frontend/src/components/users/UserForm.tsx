import React, { useState } from "react";
import { User } from "../../types";

interface UserFormProps {
    onSubmit: (user: Omit<User, "id">) => void;
    onCancel: () => void;
    initialData?: Partial<User>; // Datos iniciales opcionales
}

const UserForm: React.FC<UserFormProps> = ({ onSubmit, onCancel, initialData }) => {
    const [formData, setFormData] = useState({
        firstName: initialData?.firstName || "",
        lastName: initialData?.lastName || "",
        email: initialData?.email || "",
        phoneNumber: initialData?.phoneNumber || "",
    });

    // Actualiza el estado cuando el usuario edita un campo
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    // Maneja el envío del formulario
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSubmit(formData); // Envía los datos al componente padre
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4 bg-white p-6 rounded shadow-lg max-w-md mx-auto">
            <h2 className="text-2xl font-bold mb-4">User Form</h2>

            {/* Campo First Name */}
            <input
                name="firstName"
                value={formData.firstName}
                onChange={handleChange}
                placeholder="First Name"
                required
                className="border p-2 rounded w-full"
            />

            {/* Campo Last Name */}
            <input
                name="lastName"
                value={formData.lastName}
                onChange={handleChange}
                placeholder="Last Name"
                required
                className="border p-2 rounded w-full"
            />

            {/* Campo Email */}
            <input
                name="email"
                type="email"
                value={formData.email}
                onChange={handleChange}
                placeholder="Email"
                required
                className="border p-2 rounded w-full"
            />

            {/* Campo Phone Number */}
            <input
                name="phoneNumber"
                value={formData.phoneNumber}
                onChange={handleChange}
                placeholder="Phone Number"
                required
                className="border p-2 rounded w-full"
            />

            {/* Botones de acción */}
            <div className="flex justify-end space-x-2">
                <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">
                    Save
                </button>
                <button type="button" onClick={onCancel} className="bg-gray-500 text-white px-4 py-2 rounded">
                    Cancel
                </button>
            </div>
        </form>
    );
};

export default UserForm;

