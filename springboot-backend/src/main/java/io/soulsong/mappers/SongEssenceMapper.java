package io.soulsong.mappers;

import io.soulsong.dtos.SpotifyDTO;
import io.soulsong.entities.SongEssence;
import org.springframework.stereotype.Component;

@Component
public class SongEssenceMapper {
    
    public SongEssence mapToSongEssence(SpotifyDTO.AudioFeatures audioFeatures) {
        SongEssence essence = new SongEssence();
        essence.setTrackId(audioFeatures.getId());
        essence.setDanceability(audioFeatures.getDanceability());
        essence.setEnergy(audioFeatures.getEnergy());
        essence.setTempo(audioFeatures.getTempo());
        essence.setValence(audioFeatures.getValence());
        return essence;
    }
}
