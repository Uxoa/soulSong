package io.soulsong.mappers;

import io.soulsong.dtos.SongEssenceDTO;
import io.soulsong.entities.SongEssence;
import org.springframework.stereotype.Component;

@Component
public class SongEssenceMapper {
    public SongEssenceDTO toDTO(SongEssence songEssence) {
        if (songEssence == null) {
            return null;
        }
        
        SongEssenceDTO dto = new SongEssenceDTO();
        dto.setId(songEssence.getId());
        dto.setSongName(songEssence.getSongName());
        dto.setTrackId(songEssence.getTrackId());
        dto.setDanceability(songEssence.getDanceability());
        dto.setEnergy(songEssence.getEnergy());
        dto.setTempo(songEssence.getTempo());
        dto.setValence(songEssence.getValence());
        dto.setDescription(songEssence.getDescription()); // Incluir la descripción
        return dto;
    }
    
    public SongEssence toEntity(SongEssenceDTO dto) {
        if (dto == null) {
            return null;
        }
        
        SongEssence songEssence = new SongEssence();
        songEssence.setId(dto.getId());
        songEssence.setSongName(dto.getSongName());
        songEssence.setTrackId(dto.getTrackId());
        songEssence.setDanceability(dto.getDanceability());
        songEssence.setEnergy(dto.getEnergy());
        songEssence.setTempo(dto.getTempo());
        songEssence.setValence(dto.getValence());
        songEssence.setDescription(dto.getDescription()); // Incluir la descripción
        return songEssence;
    }
}
