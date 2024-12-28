package io.soulsong.controllers;

import io.soulsong.dtos.SongDTO;
import io.soulsong.services.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/songs")
public class SongController {
    
    private final SongService songService;
    
    public SongController(SongService songService) {
        this.songService = songService;
    }
    
    @PostMapping("/add/{profileId}")
    public ResponseEntity<?> addSongToProfile(
          @PathVariable Long profileId, // Cambia @RequestParam por @PathVariable
          @Valid @RequestBody SongDTO songDTO) {
        try {
            return ResponseEntity.ok(songService.addSongToProfile(profileId, songDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }
}
