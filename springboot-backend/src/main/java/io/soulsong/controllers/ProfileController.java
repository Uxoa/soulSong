package io.soulsong.controllers;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.services.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    
    private final ProfileService profileService;
    
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    /**
     * Crear un nuevo perfil.
     */
    @PostMapping
    public ResponseEntity<?> createProfile(@Valid @RequestBody ProfileDTO profileDTO) {
        try {
            ProfileDTO savedProfile = profileService.createProfile(profileDTO);
            return ResponseEntity.ok(savedProfile);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    /**
     * Obtener todos los perfiles.
     */
    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }
    
    /**
     * Obtener un perfil por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProfileDTO>> getProfile(@PathVariable Long id) {
        Optional<ProfileDTO> profileDTO = profileService.getProfile(id);
        return ResponseEntity.ok(profileDTO);
    }
    
    /**
     * Actualizar un perfil existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long id, @Valid @RequestBody ProfileDTO profileDTO) {
        try {
            ProfileDTO updatedProfile = profileService.updateProfile(id, profileDTO);
            return ResponseEntity.ok(updatedProfile);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Eliminar un perfil.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
