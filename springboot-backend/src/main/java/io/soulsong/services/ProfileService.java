package io.soulsong.services;

import io.soulsong.dtos.SongDTO;
import io.soulsong.dtos.ProfileDTO;
import io.soulsong.entities.Song;
import io.soulsong.entities.Profile;
import io.soulsong.entities.SongEssence;
import io.soulsong.entities.User;
import io.soulsong.mappers.SongMapper;
import io.soulsong.mappers.ProfileMapper;
import io.soulsong.repositories.SongRepository;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.SongEssenceRepository;
import io.soulsong.repositories.UserRepository;
import io.soulsong.services.external.SpotifyService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final SongEssenceRepository songEssenceRepository;
    private final SongRepository songRepository;
    private final SpotifyService spotifyService;
    
    public ProfileService(UserRepository userRepository,
                          ProfileRepository profileRepository,
                          SongEssenceRepository songEssenceRepository,
                          SongRepository songRepository,
                          SpotifyService spotifyService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.songEssenceRepository = songEssenceRepository;
        this.songRepository = songRepository;
        this.spotifyService = spotifyService;
    }
    
    @Transactional
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        User user = userRepository.findById(profileDTO.getUserId())
              .orElseThrow(() -> new RuntimeException("Para crear un perfil, debe registrarse"));
        
        Profile profile = ProfileMapper.toEntity(profileDTO);
        profile.setUser(user);
        
        Profile savedProfile = profileRepository.save(profile);
        return ProfileMapper.toDTO(savedProfile);
    }
    
    @Transactional
    public void deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new EntityNotFoundException("Perfil no encontrado");
        }
        profileRepository.deleteById(id);
    }
    
    public Optional<ProfileDTO> getProfile(Long id) {
        return profileRepository.findById(id)
              .map(ProfileMapper::toDTO);
    }
    
    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll().stream()
              .map(ProfileMapper::toDTO)
              .collect(Collectors.toList());
    }
    
    @Transactional
    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
        Profile profile = profileRepository.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado"));
        
        profile.setUserName(profileDTO.getUserName());
        
        Profile updatedProfile = profileRepository.save(profile);
        return ProfileMapper.toDTO(updatedProfile);
    }
    
    @Transactional
    public SongDTO addSong(Long profileId, SongDTO songDTO) {
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado"));
        
        SongEssence songEssence = songEssenceRepository.findById(songDTO.getSongEssence().getId())
              .orElseGet(() -> songEssenceRepository.save(songDTO.getSongEssence().toEntity()));
        
        Song song = new Song();
        song.setProfile(profile);
        song.setSongEssence(songEssence);
        song.setAddedDate(LocalDateTime.now());
        
        Song savedSong = songRepository.save(song);
        return SongMapper.toDTO(savedSong);
    }
    
    @Transactional
    public void removeSong(Long profileId, Long songId) {
        Song song = songRepository.findById(songId)
              .orElseThrow(() -> new EntityNotFoundException("Canción no encontrada"));
        
        if (!song.getProfile().getId().equals(profileId)) {
            throw new RuntimeException("La canción no pertenece al perfil especificado");
        }
        
        songRepository.delete(song);
    }
    
    @Transactional
    public void emptyDataProfile(Long id) {
        Profile profile = profileRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        
        profile.setUserName(null);
        profile.setAvatar(null);
        profile.setSongs(null);
    }
    
    public List<SongDTO> getSongs(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado"));
        
        return profile.getSongs().stream()
              .map(SongMapper::toDTO)
              .collect(Collectors.toList());
    }
    
    
    /**
     * Encuentra perfiles compatibles según la SongEssence de una canción.
     *
     * @param trackId El ID de la canción en Spotify.
     * @return Lista de perfiles ordenados por compatibilidad.
     */
    public List<Profile> findCompatibleProfiles(String trackId) {
        // Obtener las características de la canción
        SongEssence targetEssence = spotifyService.getAudioFeatures(trackId);
        
        // Obtener todos los perfiles
        List<Profile> allProfiles = profileRepository.findAll();
        
        // Ordenar perfiles por proximidad a la SongEssence objetivo
        return allProfiles.stream()
              .sorted(Comparator.comparingDouble(profile -> calculateDistance(
                    profile.getSongs().isEmpty() ? null : profile.getSongs().get(0).getSongEssence(), // Usa la primera canción si existe
                    targetEssence
              )))
              .collect(Collectors.toList());
    }
    
    /**
     * Calcula la distancia entre dos SongEssence.
     *
     * @param essence1 SongEssence de un perfil.
     * @param essence2 SongEssence objetivo.
     * @return Distancia calculada (cuanto menor, más compatible).
     */
    private double calculateDistance(SongEssence essence1, SongEssence essence2) {
        if (essence1 == null || essence2 == null) {
            return Double.MAX_VALUE; // Penalizar perfiles sin SongEssence
        }
        return Math.sqrt(
              Math.pow(essence1.getDanceability() - essence2.getDanceability(), 2) +
                    Math.pow(essence1.getEnergy() - essence2.getEnergy(), 2) +
                    Math.pow(essence1.getValence() - essence2.getValence(), 2) +
                    Math.pow(essence1.getTempo() - essence2.getTempo(), 2)
        );
    }
    
}
