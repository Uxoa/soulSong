package io.soulsong.controllers;

import io.soulsong.dtos.ImageProfileDTO;
import io.soulsong.services.ImageProfileService;
import io.soulsong.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    
    private final ProfileService profileService;
    private final ImageProfileService imageProfileService;
    
    public ProfileController(ProfileService profileService, ImageProfileService imageProfileService) {
        this.profileService = profileService;
        this.imageProfileService = imageProfileService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProfile(@PathVariable Long id) {
        return profileService.getProfile(id)
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{id}/image")
    public ResponseEntity<ImageProfileDTO> uploadImage(@PathVariable Long id, @Valid @RequestBody ImageProfileDTO imageProfileDTO) {
        ImageProfileDTO savedImage = imageProfileService.uploadImage(id, imageProfileDTO);
        return ResponseEntity.ok(savedImage);
    }
    
    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable Long id) {
        return imageProfileService.getImage(id)
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}/image")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageProfileService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
