import React, { useState } from 'react';
import { UserDTO } from '../../api-services/users.services';

interface UserFormProps {
    user?: UserDTO; // Cambiamos null por undefined
    onSave: (user: UserDTO) => void;
    onCancel: () => void;
}

const UserForm: React.FC<UserFormProps> = ({ user, onSave, onCancel }) => {
    const [formData, setFormData] = useState<UserDTO>(
        user || { name: '', email: '' }
    );

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSave(formData);
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Nombre:</label>
                <input
                    type="text"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label>Email:</label>
                <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                />
            </div>
            <button type="submit">Guardar</button>
            <button type="button" onClick={onCancel}>
                Cancelar
            </button>
        </form>
    );
};

export default UserForm;
