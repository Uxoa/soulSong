package io.soulsong.controllers;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.entities.Profile;
import io.soulsong.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    
    private final ProfileService profileService;
    
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    @PostMapping
    public ResponseEntity<?> createProfile(@Valid @RequestBody ProfileDTO profileDTO) {
        try {
            ProfileDTO savedProfile = profileService.createProfile(profileDTO);
            return ResponseEntity.ok(savedProfile);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) {
        return profileService.getProfile(id)
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> editProfile(@PathVariable Long id, @Valid @RequestBody ProfileDTO profileDTO) {
        return ResponseEntity.ok(profileService.updateProfile(id, profileDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}/empty-data")
    public ResponseEntity<Void> emptyDataProfile(@PathVariable Long id) {
        profileService.emptyDataProfile(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/match-profiles")
    public List<Profile> matchProfiles(@RequestParam String trackId) {
        return profileService.findCompatibleProfiles(trackId);
    }
}
