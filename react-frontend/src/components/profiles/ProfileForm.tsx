import { useState } from 'react';
import { ProfileDTO } from "../../types";
import { createProfile, updateProfile } from '../../api-services/profiles.services';

interface ProfileFormProps {
    profile?: ProfileDTO; // Tipo explícito para perfiles
    onSave: () => void; // Callback al guardar
}

const ProfileForm: React.FC<ProfileFormProps> = ({ profile, onSave }) => {
    const [formData, setFormData] = useState<Omit<ProfileDTO, 'id'>>(
        profile
            ? {
                bio: profile.bio,
                favoriteGenres: profile.favoriteGenres,
                userId: profile.userId,
            }
            : { bio: '', favoriteGenres: [], userId: 0 } // Valores iniciales para un nuevo perfil
    );

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: name === 'favoriteGenres' ? value.split(',') : value,
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            if (profile?.id) {
                await updateProfile(profile.id, formData);
            } else {
                await createProfile(formData);
            }
            onSave(); // Notifica que se guardó correctamente
        } catch (error) {
            console.error('Error al guardar el perfil:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="form-container">
            <h2>{profile ? 'Editar Perfil' : 'Crear Perfil'}</h2>
            <div>
                <label htmlFor="bio">Biografía:</label>
                <input
                    id="bio"
                    type="text"
                    name="bio"
                    value={formData.bio}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="favoriteGenres">Géneros Favoritos (separados por comas):</label>
                <input
                    id="favoriteGenres"
                    type="text"
                    name="favoriteGenres"
                    value={formData.favoriteGenres.join(',')}
                    onChange={handleChange}
                    required
                />
            </div>
            <button type="submit">{profile ? 'Actualizar' : 'Crear'}</button>
        </form>
    );
};

export default ProfileForm;

