package io.soulsong.mappers;

import io.soulsong.dtos.SongDTO;
import io.soulsong.entities.Song;

public class SongMapper {
    
    /**
     * Convierte un DTO de FavoriteSong a una entidad.
     * @param favoriteSongDTO El DTO de FavoriteSong.
     * @return La entidad FavoriteSong.
     */
    public static Song toEntity(SongDTO favoriteSongDTO) {
        if (favoriteSongDTO == null) {
            return null;
        }
        
        Song favoriteSong = new Song();
        favoriteSong.setId(favoriteSongDTO.getId());
        favoriteSong.setAddedDate(favoriteSongDTO.getAddedDate());
        
        // Nota: `profile` y `songEssence` deben establecerse en el servicio.
        return favoriteSong;
    }
    
    /**
     * Convierte una entidad FavoriteSong a un DTO.
     * @param song La entidad FavoriteSong.
     * @return El DTO de FavoriteSong.
     */
    public static SongDTO toDTO(Song song) {
        if (song == null) {
            return null;
        }
        
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
        
        if (song.getProfile() != null) {
            songDTO.setProfileId(song.getProfile().getId());
        }
        
        if (song.getSongEssence() != null) {
            songDTO.setSongEssence(SongEssenceMapper.toDTO(song.getSongEssence()));
            songDTO.setTrackId(song.getSongEssence().getTrackId());
        }
        
        songDTO.setAddedDate(song.getAddedDate());
        return songDTO;
    }
}
