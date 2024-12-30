package io.soulsong.services;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.dtos.SongDTO;
import io.soulsong.entities.Profile;
import io.soulsong.entities.SongEssence;
import io.soulsong.entities.User;
import io.soulsong.mappers.ProfileMapper;
import io.soulsong.mappers.SongEssenceMapper;
import io.soulsong.mappers.SongMapper;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.SongRepository;
import io.soulsong.repositories.UserRepository;
import io.soulsong.services.SongEssenceService;
import io.soulsong.services.external.SpotifyService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final SongRepository songRepository;
    private final SpotifyService spotifyService;
    private final SongEssenceService songEssenceService;
    private final SongMapper songMapper;
    private final ProfileMapper profileMapper;
    private final SongEssenceMapper songEssenceMapper;
    
    public ProfileService(UserRepository userRepository,
                          ProfileRepository profileRepository,
                          SongRepository songRepository,
                          SpotifyService spotifyService,
                          SongEssenceService songEssenceService,
                          SongMapper songMapper,
                          ProfileMapper profileMapper,
                          SongEssenceMapper songEssenceMapper) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.songRepository = songRepository;
        this.spotifyService = spotifyService;
        this.songEssenceService = songEssenceService;
        this.songMapper = songMapper;
        this.profileMapper = profileMapper;
        this.songEssenceMapper = songEssenceMapper;
    }
    
    /**
     * Crea un perfil solo si el usuario está registrado.
     */
    @Transactional
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        User user = userRepository.findById(profileDTO.getUserId())
              .orElseThrow(() -> new RuntimeException("Debe registrarse como usuario para crear un perfil."));
        
        Profile profile = profileMapper.toEntity(profileDTO);
        profile.setUser(user);
        
        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.toDTO(savedProfile);
    }
    
    /**
     * Actualiza un perfil existente.
     */
    @Transactional
    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
        Profile profile = profileRepository.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado."));
        
        profile.setUserName(profileDTO.getUserName());
        profile.setAvatarUrl(profileDTO.getAvatarUrl());
        
        Profile updatedProfile = profileRepository.save(profile);
        return profileMapper.toDTO(updatedProfile);
    }
    
    @Transactional
    public void deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new EntityNotFoundException("Perfil no encontrado.");
        }
        profileRepository.deleteById(id);
    }
    
    @Transactional
    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll().stream()
              .map(profile -> {
                  ProfileDTO profileDTO = profileMapper.toDTO(profile);
                  profileDTO.setAvatarUrl(buildAvatarUrl(profile.getAvatarUrl()));
                  return profileDTO;
              })
              .collect(Collectors.toList());
    }
    
    @Transactional
    public Optional<ProfileDTO> getProfile(Long id) {
        return profileRepository.findById(id)
              .map(profile -> {
                  ProfileDTO profileDTO = profileMapper.toDTO(profile);
                  
                  // Generar descripciones para cada SongEssence
                  profileDTO.setSongs(
                        profile.getSongs().stream()
                              .map(song -> {
                                  if (song.getSongEssence() != null) {
                                      SongEssence essence = song.getSongEssence();
                                      
                                      // Si la descripción no existe, generar una nueva
                                      if (essence.getDescription() == null || essence.getDescription().isEmpty()) {
                                          String description = songEssenceService.analyzeDescription(essence);
                                          essence.setDescription(description);
                                      }
                                  }
                                  return songMapper.toDTO(song);
                              })
                              .collect(Collectors.toList())
                  );
                  
                  profileDTO.setAvatarUrl(buildAvatarUrl(profile.getAvatarUrl()));
                  return profileDTO;
              });
    }
    
    
    private String buildAvatarUrl(String avatar) {
        if (avatar == null || avatar.isEmpty()) {
            return "http://localhost:5173/images/avatar01.png"; // Avatar por defecto
        }
        if (avatar.startsWith("http")) {
            return avatar; // URL absoluta
        }
        return "http://localhost:5173" + avatar; // Construir URL relativa
    }
}
