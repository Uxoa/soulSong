package io.soulsong.mappers;

import io.soulsong.dtos.SongDTO;
import io.soulsong.entities.Song;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {
    
    private final SongEssenceMapper songEssenceMapper;
    
    public SongMapper(SongEssenceMapper songEssenceMapper) {
        this.songEssenceMapper = songEssenceMapper;
    }
    
    public Song toEntity(SongDTO songDTO) {
        if (songDTO == null) {
            return null;
        }
        
        Song song = new Song();
        song.setId(songDTO.getId());
        song.setAddedDate(songDTO.getAddedDate());
        // Las relaciones como Profile y SongEssence deben manejarse en el servicio
        return song;
    }
    
    public SongDTO toDTO(Song song) {
        if (song == null) {
            return null;
        }
        
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
        songDTO.setAddedDate(song.getAddedDate());
        
        if (song.getProfile() != null) {
            songDTO.setProfileId(song.getProfile().getId());
        }
        
        if (song.getSongEssence() != null) {
            songDTO.setSongEssence(songEssenceMapper.toDTO(song.getSongEssence()));
        }
        
        return songDTO;
    }
}
