package io.soulsong.controllers;


import io.soulsong.entities.SongEssence;
import io.soulsong.services.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
public class SongEssenceController {
    
    private final SpotifyService spotifyService;
    
    @Autowired
    public SongEssenceController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }
    
    @GetMapping("/analyze/{trackId}")
    public SongEssence analyzeTrack(@PathVariable String trackId) {
        return spotifyService.getAudioFeatures(trackId);
    }
    
    @GetMapping("/search")
    public Object searchTracks(@RequestParam String query) {
        return spotifyService.searchTracks(query);
    }
}
