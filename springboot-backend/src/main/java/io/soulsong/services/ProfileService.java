package io.soulsong.services;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.entities.Profile;
import io.soulsong.entities.User;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    
    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }
    
    public Optional<ProfileDTO> getProfile(Long id) {
        return profileRepository.findById(id).map(ProfileDTO::fromEntity);
    }
    
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        User user = userRepository.findById(profileDTO.getUserId())
              .orElseThrow(() -> new RuntimeException("User not found"));
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setName(profileDTO.getName());
        profile.setEmail(profileDTO.getEmail());
        Profile savedProfile = profileRepository.save(profile);
        return ProfileDTO.fromEntity(savedProfile);
    }
    
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
    
    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
        Profile profile = profileRepository.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Profile not found"));
        profile.setName(profileDTO.getName());
        profile.setEmail(profileDTO.getEmail());
        Profile updatedProfile = profileRepository.save(profile);
        return ProfileDTO.fromEntity(updatedProfile);
    }
    
    public boolean isUserRegistered(Long userId) {
        return userRepository.existsById(userId);
    }
    
    public void emptyDataProfile(Long id) {
        Profile profile = profileRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Profile not found"));
        profile.getFavoriteSongs().clear();
        profileRepository.save(profile);
    }
}