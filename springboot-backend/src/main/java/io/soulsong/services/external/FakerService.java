package io.soulsong.services;

import io.soulsong.entities.SongEssence;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakerService {
    
    private final Random random = new Random();
    
    /**
     * Genera atributos de SongEssence simulados.
     *
     * @param trackId El ID de la canción.
     * @return Atributos simulados de SongEssence.
     */
    public SongEssence generateFakeSongEssence(String trackId) {
        SongEssence songEssence = new SongEssence();
        songEssence.setSongName("Fake Song for Track ID " + trackId);
        songEssence.setTempo(80 + random.nextDouble() * 100); // Tempo entre 80 y 180
        songEssence.setDanceability(random.nextDouble());     // Danceability entre 0 y 1
        songEssence.setEnergy(random.nextDouble());           // Energy entre 0 y 1
        songEssence.setValence(random.nextDouble());          // Valence entre 0 y 1
        songEssence.setTrackId(trackId);
        return songEssence;
    }
}
