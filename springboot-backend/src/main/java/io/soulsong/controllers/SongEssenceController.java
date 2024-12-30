package io.soulsong.controllers;

import io.soulsong.dtos.SongEssenceDTO;
import io.soulsong.services.SongEssenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/songs")
public class SongEssenceController {
    
    private final SongEssenceService songEssenceService;
    
    public SongEssenceController(SongEssenceService songEssenceService) {
        this.songEssenceService = songEssenceService;
    }
    
    /**
     * Endpoint para analizar una canción.
     *
     * @param trackId El ID de la canción.
     * @return Atributos de SongEssence y una descripción generada.
     */
    @GetMapping("/analyze/{trackId}")
    public ResponseEntity<Map<String, Object>> analyzeSong(@PathVariable String trackId) {
        Map<String, Object> result = songEssenceService.analyzeSongWithAI(trackId);
        return ResponseEntity.ok(result);
    }
}
