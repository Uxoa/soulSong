package io.soulsong.controllers;

import io.soulsong.entities.SongEssence;
import io.soulsong.services.SongEssenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/songs")
public class SongEssenceController {
    
    private final SongEssenceService songEssenceService;
    
    public SongEssenceController(SongEssenceService songEssenceService) {
        this.songEssenceService = songEssenceService;
    }
    
    @GetMapping("/essence")
    public ResponseEntity<SongEssence> getSongEssence(@RequestParam String trackId) {
        SongEssence essence = songEssenceService.getSongEssence(trackId);
        return ResponseEntity.ok(essence);
    }
}

