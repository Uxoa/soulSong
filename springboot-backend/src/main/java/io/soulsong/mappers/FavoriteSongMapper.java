package io.soulsong.mappers;

import io.soulsong.dtos.FavoriteSongDTO;
import io.soulsong.entities.FavoriteSong;

public class FavoriteSongMapper {
    
    /**
     * Convierte un DTO de FavoriteSong a una entidad.
     * @param favoriteSongDTO El DTO de FavoriteSong.
     * @return La entidad FavoriteSong.
     */
    public static FavoriteSong toEntity(FavoriteSongDTO favoriteSongDTO) {
        if (favoriteSongDTO == null) {
            return null;
        }
        
        FavoriteSong favoriteSong = new FavoriteSong();
        favoriteSong.setId(favoriteSongDTO.getId());
        favoriteSong.setAddedDate(favoriteSongDTO.getAddedDate());
        
        // Nota: `profile` y `songEssence` deben establecerse en el servicio.
        return favoriteSong;
    }
    
    /**
     * Convierte una entidad FavoriteSong a un DTO.
     * @param favoriteSong La entidad FavoriteSong.
     * @return El DTO de FavoriteSong.
     */
    public static FavoriteSongDTO toDTO(FavoriteSong favoriteSong) {
        if (favoriteSong == null) {
            return null;
        }
        
        FavoriteSongDTO favoriteSongDTO = new FavoriteSongDTO();
        favoriteSongDTO.setId(favoriteSong.getId());
        
        if (favoriteSong.getProfile() != null) {
            favoriteSongDTO.setProfileId(favoriteSong.getProfile().getId());
        }
        
        if (favoriteSong.getSongEssence() != null) {
            favoriteSongDTO.setSongEssence(SongEssenceMapper.toDTO(favoriteSong.getSongEssence()));
            favoriteSongDTO.setTrackId(favoriteSong.getSongEssence().getTrackId());
        }
        
        favoriteSongDTO.setAddedDate(favoriteSong.getAddedDate());
        return favoriteSongDTO;
    }
}
