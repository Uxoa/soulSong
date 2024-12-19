package io.soulsong.controllers;

import io.soulsong.entities.Profile;
import io.soulsong.services.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    
    private final ProfileService profileService;
    
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }
    
    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }
    
    @PostMapping("/{userId}")
    public Profile createProfile(@RequestBody Profile profile, @PathVariable Long userId) {
        return profileService.createProfile(profile, userId);
    }
    
    @PutMapping("/{id}")
    public Profile updateProfile(@RequestBody Profile profile, @PathVariable Long id) {
        return profileService.updateProfile(profile, id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
    }
    
    
}
