import axios from "axios";

export const analyzeSong = async (trackId: string) => {
    const response = await axios.get(`http://localhost:8080/songs/analyze/${trackId}`);
    return response.data;
};
