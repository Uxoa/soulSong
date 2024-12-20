package io.soulsong.controllers;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.entities.Profile;
import io.soulsong.entities.SongEssence;
import io.soulsong.exceptions.ResourceNotFoundException;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.SongEssenceRepository;
import io.soulsong.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    
    private final ProfileService profileService;
    private final ProfileRepository profileRepository;
    private final SongEssenceRepository songEssenceRepository;
    
    public ProfileController(ProfileService profileService, ProfileRepository profileRepository, SongEssenceRepository songEssenceRepository) {
        this.profileService = profileService;
        this.profileRepository = profileRepository;
        this.songEssenceRepository = songEssenceRepository;
    }
    
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return ResponseEntity.ok(profiles);
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
    
    @PostMapping("/{profileId}/favorite-songs/{trackId}")
    public ResponseEntity<Void> addFavoriteSong(@PathVariable Long profileId,
                                                @PathVariable Long trackId) {
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        
        SongEssence songEssence = songEssenceRepository.findById(trackId)
              .orElseThrow(() -> new ResourceNotFoundException("Song not found"));
        profile.addFavoriteSong(songEssence);
        profileRepository.save(profile);
        
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{profileId}/favorite-songs")
    public ResponseEntity<List<SongEssence>> getFavoriteSongs(@PathVariable Long profileId) {
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        
        return ResponseEntity.ok(profile.getFavoriteSongs());
    }
    
    
}
