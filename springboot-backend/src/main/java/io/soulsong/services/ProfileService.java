package io.soulsong.services;

import io.soulsong.entities.Profile;
import io.soulsong.entities.User;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
