import { useState } from "react";
import { analyzeSong } from "../../api-services/songEssence.service";
import { SongEssenceResult } from "../../types"; // Asegúrate de que la ruta sea correcta

const SongAnalyzer = () => {
    const [trackId, setTrackId] = useState<string>(""); // Especificamos el tipo explícitamente
    const [result, setResult] = useState<SongEssenceResult | null>(null); // Cambiamos any por SongEssenceResult
    const [loading, setLoading] = useState<boolean>(false); // Especificamos el tipo boolean

    const handleAnalyze = async () => {
        try {
            setLoading(true);
            const data = await analyzeSong(trackId); // Llamada al servicio
            setResult(data); // Guardamos los datos en el estado
        } catch (err) {
            console.error("Error analyzing song:", err);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <h1>Song Analyzer</h1>
            <input
                type="text"
                value={trackId}
                onChange={(e) => setTrackId(e.target.value)}
                placeholder="Enter Track ID"
            />
            <button onClick={handleAnalyze} disabled={loading}>
                {loading ? "Analyzing..." : "Analyze"}
            </button>
            {result && (
                <div>
                    <h3>Song Essence</h3>
                    <p>Tempo: {result.songEssence.tempo}</p>
                    <p>Danceability: {result.songEssence.danceability}</p>
                    <p>Energy: {result.songEssence.energy}</p>
                    <p>Valence: {result.songEssence.valence}</p>
                    <h3>Description</h3>
                    <p>{result.description}</p>
                </div>
            )}
        </div>
    );
};

export default SongAnalyzer;
