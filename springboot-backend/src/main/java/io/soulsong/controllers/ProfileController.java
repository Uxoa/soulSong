package io.soulsong.controllers;

import io.soulsong.entities.User;
import io.soulsong.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    
    private final ProfileService profileService;
    
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    @PostMapping("/{userId}/add-song")
    public ResponseEntity<?> addFavoriteSong(@PathVariable Long userId, @RequestParam String trackId) {
        // Simulamos la búsqueda del usuario (normalmente esto sería un repositorio)
        User user = new User();
        user.setFirstName("Paloma");
        
        try {
            profileService.addSongToProfile(user, trackId);
            return ResponseEntity.ok("Canción agregada al perfil con éxito.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
