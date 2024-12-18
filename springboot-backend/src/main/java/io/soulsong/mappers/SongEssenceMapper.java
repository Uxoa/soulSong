package io.soulsong.mappers;

import io.soulsong.dtos.SpotifyDTO;
import io.soulsong.entities.SongEssence;

public class SongEssenceMapper {
    
    // Método para mapear un SpotifyDTO a SongEssence
    public static SongEssence mapToSongEssence(SpotifyDTO dto) {
        return new SongEssence(
              dto.trackId(),       // trackId desde el DTO
              "No implemented",      // Mood, aún no implementado
              dto.energy(),        // Energy
              dto.danceability(),  // Danceability
              dto.tempo(),         // Tempo
              dto.acousticness()   // Acousticness
        );
    }
}
