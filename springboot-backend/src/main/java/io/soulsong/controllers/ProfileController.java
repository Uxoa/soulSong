package io.soulsong.controllers;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.dtos.SongEssenceDTO;
import io.soulsong.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
        List<ProfileDTO> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) {
        return profileService.getProfile(id)
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());
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
    
    @PostMapping("/{profileId}/favorite-songs/{trackId}")
    public ResponseEntity<Void> addFavoriteSong(@PathVariable Long profileId,
                                                @PathVariable Long trackId) {
        profileService.addFavoriteSong(profileId, trackId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @DeleteMapping("/{profileId}/favorite-songs/{trackId}")
    public ResponseEntity<Void> removeFavoriteSong(@PathVariable Long profileId,
                                                   @PathVariable Long trackId) {
        profileService.removeFavoriteSong(profileId, trackId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{profileId}/favorite-songs")
    public ResponseEntity<List<SongEssenceDTO>> getFavoriteSongs(@PathVariable Long profileId) {
        List<SongEssenceDTO> favoriteSongs = profileService.getFavoriteSongs(profileId);
        return ResponseEntity.ok(favoriteSongs);
    }
}
