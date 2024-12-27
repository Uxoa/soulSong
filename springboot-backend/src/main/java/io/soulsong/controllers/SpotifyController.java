package io.soulsong.controllers;

import io.soulsong.dtos.SpotifyDTO;
import io.soulsong.services.external.SpotifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotifyController {
    private final SpotifyService spotifyService;
    
    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }
    
    @GetMapping("/api/user-profile")
    public SpotifyDTO.UserProfile getUserProfile() {
        return spotifyService.getCurrentUserProfile();
    }
}
