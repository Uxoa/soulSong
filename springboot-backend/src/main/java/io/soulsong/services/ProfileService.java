package io.soulsong.services;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.dtos.SongEssenceDTO;
import io.soulsong.entities.Profile;
import io.soulsong.entities.User;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    
    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }
    
 
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        // Verifica si el usuario existe
        if (!userRepository.existsById(profileDTO.getUserId())) {
            throw new RuntimeException("Para crear un perfil, debe registrarse");
        }
        
        // Si el usuario existe, procede a crear el perfil
        User user = userRepository.findById(profileDTO.getUserId())
              .orElseThrow(() -> new RuntimeException("User not found"));
        
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setUserName(profileDTO.getUserName());
        
        Profile savedProfile = profileRepository.save(profile);
        return ProfileDTO.fromEntity(savedProfile);
    }
    
    public void deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new EntityNotFoundException("Profile not found");
        }
        profileRepository.deleteById(id);
    }
    
    public Optional<ProfileDTO> getProfile(Long id) {
        return profileRepository.findById(id)
              .map(ProfileDTO::fromEntity);
    }
    
    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll().stream()
              .map(ProfileDTO::fromEntity)
              .collect(Collectors.toList());
    }
    
    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
        // Buscar el perfil
        Profile profile = profileRepository.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        
        // Actualizar información del perfil
        profile.setUserName(profileDTO.getUserName());
        
        // Actualizar canciones favoritas (si se incluyen en el DTO)
        if (profileDTO.getFavoriteSongs() != null) {
            profile.getFavoriteSongs().clear();
            profile.getFavoriteSongs().addAll(
                  profileDTO.getFavoriteSongs().stream()
                        .map(SongEssenceDTO::toEntity)
                        .collect(Collectors.toList())
            );
        }
        
        Profile updatedProfile = profileRepository.save(profile);
        return ProfileDTO.fromEntity(updatedProfile);
    }
    
    public boolean isUserRegistered(Long userId) {
        return userRepository.existsById(userId);
    }
    
    
    public void addFavoriteSong(Long profileId, Long trackId) {
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        
        // Buscar la canción y agregarla a la lista de favoritos
        profile.addFavoriteSong(
              profile.getSongEssences().stream()
                    .filter(song -> song.getId().equals(trackId))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Song not found"))
        );
        
        profileRepository.save(profile);
    }
    
    public void removeFavoriteSong(Long profileId, Long trackId) {
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        
        // Buscar la canción y removerla de la lista de favoritos
        profile.removeFavoriteSong(
              profile.getFavoriteSongs().stream()
                    .filter(song -> song.getId().equals(trackId))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Song not found"))
        );
        
        profileRepository.save(profile);
    }
    
    public void emptyDataProfile(Long id) {
        Profile profile = profileRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Profile not found"));
        
        // Vaciar canciones favoritas
        profile.getFavoriteSongs().clear();
        profileRepository.save(profile);
    }
    
    public List<SongEssenceDTO> getFavoriteSongs(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        
        return profile.getFavoriteSongs().stream()
              .map(SongEssenceDTO::fromEntity)
              .collect(Collectors.toList());
    }
    
    
    
}
