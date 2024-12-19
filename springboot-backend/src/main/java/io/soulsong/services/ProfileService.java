package io.soulsong.services;

import io.soulsong.entities.Profile;
import io.soulsong.entities.User;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public Profile createProfile(Profile profile, Long userId) {
        User user = userRepository.findById(userId)
              .orElseThrow(() -> new RuntimeException("User not found"));
        profile.setUser(user);
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Profile profile, Long id) {
        Profile profileToUpdate = profileRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Profile not found"));
        profileToUpdate.setFavoriteSongs(profile.getFavoriteSongs());
        return profileRepository.save(profileToUpdate);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    public List<Profile> getProfilesByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId).stream()
              .findFirst()
              .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public void deleteProfileByUserId(Long userId) {
        profileRepository.deleteByUserId(userId);
    }
    
    public Optional<Object> getProfile(Long id) {
        return Optional.of(profileRepository.findById(id));
        
    }
    
    
    
}