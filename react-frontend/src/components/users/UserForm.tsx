import React, { useState } from "react";
import { UserDTO } from "../../types";

interface UserFormProps {
    user?: UserDTO;
    onSave: (user: UserDTO) => void;
    onCancel: () => void;
    onClose: () => void; // Agregado onClose aquí
}

const defaultUser: UserDTO = {
    name: "",
    email: "",
    role: "", // Asegúrate de que esto existe en UserDTO
    favoriteSongs: [],
};

const UserForm: React.FC<UserFormProps> = ({ user, onSave, onCancel, onClose }) => {
    const [formData, setFormData] = useState<UserDTO>(user || defaultUser);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSave(formData);
        onClose(); // Llamada onClose al guardar
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Nombre:</label>
                <input
                    type="text"
                    name="name"
                    value={formData.name || ""}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label>Email:</label>
                <input
                    type="email"
                    name="email"
                    value={formData.email || ""}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label>Role:</label>
                <input
                    type="text"
                    name="role"
                    value={formData.role || ""}
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
