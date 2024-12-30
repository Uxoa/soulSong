import axios from "axios";

const API_BASE_URL = "http://localhost:8080"; // Cambia a tu URL de backend

/**
 * Realiza la solicitud al backend para analizar una canción.
 *
 * @param trackId El ID de la canción a analizar.
 * @returns Los datos del análisis de SongEssence.
 */
export const analyzeSong = async (trackId: string) => {
    const response = await axios.get(`${API_BASE_URL}/songs/analyze/${trackId}`);
    return response.data; // El backend devuelve un objeto con songEssence y description
};
