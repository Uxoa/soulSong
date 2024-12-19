package io.soulsong.controllers;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    
    private final ProfileService profileService;
    
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) {
        Optional<ProfileDTO> profile = profileService.getProfile(id);
        return profile.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileDTO profileDTO) {
        if (!profileService.isUserRegistered(profileDTO.getUserId())) {
            return ResponseEntity.badRequest().body(null);
        }
        ProfileDTO savedProfile = profileService.createProfile(profileDTO);
        return ResponseEntity.ok(savedProfile);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> editProfile(@PathVariable Long id, @Valid @RequestBody ProfileDTO profileDTO) {
        ProfileDTO updatedProfile = profileService.updateProfile(id, profileDTO);
        return ResponseEntity.ok(updatedProfile);
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
}
