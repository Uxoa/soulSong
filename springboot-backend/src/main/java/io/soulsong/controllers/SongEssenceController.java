package io.soulsong.controllers;

import io.soulsong.dtos.SongEssenceDTO;
import io.soulsong.services.SongEssenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongEssenceController {
    
    private final SongEssenceService songEssenceService;
    
    public SongEssenceController(SongEssenceService songEssenceService) {
        this.songEssenceService = songEssenceService;
    }
    
    @GetMapping
    public ResponseEntity<List<SongEssenceDTO>> getAll() {
        return ResponseEntity.ok(songEssenceService.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SongEssenceDTO> getById(@PathVariable Long id) {
        return songEssenceService.getById(id)
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<SongEssenceDTO> create(@RequestBody SongEssenceDTO songEssenceDTO) {
        SongEssenceDTO created = songEssenceService.createSongEssence(songEssenceDTO);
        return ResponseEntity.ok(created);
    }
}
