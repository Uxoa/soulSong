package io.soulsong.controllers;

import io.soulsong.dtos.FavoriteSongDTO;
import io.soulsong.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteSongController {
    
    private final ProfileService profileService;
    
    public FavoriteSongController(ProfileService profileService) {
        this.profileService = profileService;
    }
    
    @PostMapping("/{profileId}")
    public ResponseEntity<FavoriteSongDTO> addFavorite(@PathVariable Long profileId, @RequestBody FavoriteSongDTO favoriteSongDTO) {
        FavoriteSongDTO savedFavorite = profileService.addFavoriteSong(profileId, favoriteSongDTO);
        return ResponseEntity.ok(savedFavorite);
    }
    
    @GetMapping("/{profileId}")
    public ResponseEntity<List<FavoriteSongDTO>> getFavorites(@PathVariable Long profileId) {
        List<FavoriteSongDTO> favorites = profileService.getFavoriteSongs(profileId);
        return ResponseEntity.ok(favorites);
    }
}
