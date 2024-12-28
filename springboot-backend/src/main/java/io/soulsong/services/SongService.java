package io.soulsong.services;

import io.soulsong.dtos.SongDTO;
import io.soulsong.entities.Profile;
import io.soulsong.entities.Song;
import io.soulsong.entities.SongEssence;
import io.soulsong.mappers.SongMapper;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.SongEssenceRepository;
import io.soulsong.repositories.SongRepository;
import io.soulsong.services.external.SpotifyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    
    private final ProfileRepository profileRepository;
    private final SongEssenceRepository songEssenceRepository;
    private final SongRepository songRepository;
    private final SpotifyService spotifyService; // Servicio para analizar la canción
    
    public SongService(ProfileRepository profileRepository,
                       SongEssenceRepository songEssenceRepository,
                       SongRepository songRepository,
                       SpotifyService spotifyService) {
        this.profileRepository = profileRepository;
        this.songEssenceRepository = songEssenceRepository;
        this.songRepository = songRepository;
        this.spotifyService = spotifyService;
    }
    
    @Transactional
    public SongDTO addSongToProfile(Long profileId, SongDTO songDTO) {
        // Verificar si el perfil existe
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        
        // Guardar la canción sin SongEssence
        Song song = new Song();
        song.setProfile(profile);
        song.setAddedDate(songDTO.getAddedDate());
        Song savedSong = songRepository.save(song);
        
        // Analizar SongEssence después de guardar la canción
        SongEssence analyzedEssence = spotifyService.analyzeSong(songDTO.getTrackId());
        savedSong.setSongEssence(analyzedEssence);
        
        // Actualizar la canción con el SongEssence analizado
        songRepository.save(savedSong);
        
        return SongMapper.toDTO(savedSong);
    }
    
}
