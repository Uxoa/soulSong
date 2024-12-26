package io.soulsong.mappers;

import io.soulsong.dtos.SpotifyDTO;
import io.soulsong.dtos.SongEssenceDTO;
import io.soulsong.entities.SongEssence;
import org.springframework.stereotype.Component;

@Component
public class SongEssenceMapper {
    
    /**
     * Mapea los datos de SpotifyDTO.AudioFeatures a una entidad SongEssence.
     *
     * @param audioFeatures Datos de características de la canción desde Spotify.
     * @param trackId       El identificador de la canción en Spotify.
     * @return Una instancia de SongEssence.
     */
    public SongEssence mapToSongEssence(SpotifyDTO.AudioFeatures audioFeatures, String trackId) {
        if (audioFeatures == null || trackId == null) {
            throw new IllegalArgumentException("audioFeatures y trackId no pueden ser null");
        }
        
        SongEssence essence = new SongEssence();
        essence.setDanceability(audioFeatures.getDanceability());
        essence.setEnergy(audioFeatures.getEnergy());
        essence.setTempo(audioFeatures.getTempo());
        essence.setValence(audioFeatures.getValence());
        essence.setTrackId(trackId); // Asigna el trackId a la entidad
        return essence;
    }
    
    /**
     * Convierte una entidad SongEssence a SongEssenceDTO.
     *
     * @param songEssence La entidad SongEssence.
     * @return Una instancia de SongEssenceDTO.
     */
    public static SongEssenceDTO toDTO(SongEssence songEssence) {
        if (songEssence == null) {
            return null;
        }
        
        return SongEssenceDTO.fromEntity(songEssence);
    }
    
    /**
     * Convierte un DTO de SongEssence a una entidad SongEssence.
     *
     * @param dto El DTO de SongEssence.
     * @return Una instancia de SongEssence.
     */
    public SongEssence toEntity(SongEssenceDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return dto.toEntity();
    }
}
