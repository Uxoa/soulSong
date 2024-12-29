package io.soulsong.services;

import io.soulsong.dtos.SongDTO;
import io.soulsong.dtos.SongEssenceDTO;
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
    private final SpotifyService spotifyService;
    private final SongMapper songMapper;
    
    public SongService(ProfileRepository profileRepository,
                       SongEssenceRepository songEssenceRepository,
                       SongRepository songRepository,
                       SpotifyService spotifyService,
                       SongMapper songMapper) {
        this.profileRepository = profileRepository;
        this.songEssenceRepository = songEssenceRepository;
        this.songRepository = songRepository;
        this.spotifyService = spotifyService;
        this.songMapper = songMapper;
    }
    
    @Transactional
    public SongEssenceDTO addSongToProfile(Long profileId, SongDTO songDTO) {
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        
        Song song = songMapper.toEntity(songDTO);
        song.setProfile(profile);
        song.setAddedDate(songDTO.getAddedDate());
        Song savedSong = songRepository.save(song);
        
        SongEssence analyzedEssence = spotifyService.analyzeSong(songDTO.getTrackId());
        savedSong.setSongEssence(analyzedEssence);
        songRepository.save(savedSong);
        
        return songMapper.toDTO(savedSong).getSongEssence();
    }
}
