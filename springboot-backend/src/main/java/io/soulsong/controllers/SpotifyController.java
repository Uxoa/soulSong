package io.soulsong.controllers;

import io.soulsong.services.SpotifyAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {
    
    private final SpotifyAuthService spotifyAuthService;
    
    public SpotifyController(SpotifyAuthService spotifyAuthService) {
        this.spotifyAuthService = spotifyAuthService;
    }
    
    @GetMapping("/token")
    public ResponseEntity<String> getSpotifyToken() {
        String token = spotifyAuthService.getAccessToken();
        return ResponseEntity.ok(token);
    }
}
