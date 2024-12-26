package io.soulsong.controllers;

import io.soulsong.dtos.SongEssenceDTO;
import io.soulsong.entities.SongEssence;
import io.soulsong.services.external.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
public class SongEssenceController {
    
    private final SpotifyService spotifyService;
    
    @Autowired
    public SongEssenceController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }
    
    /**
     * Analiza las características de audio de una canción por su ID.
     *
     * @param trackId El ID de la canción.
     * @return Una ResponseEntity con los detalles de la canción.
     */
    @GetMapping("/analyze/{trackId}")
    public ResponseEntity<SongEssenceDTO> analyzeTrack(@PathVariable String trackId) {
        try {
            SongEssence songEssence = spotifyService.getAudioFeatures(trackId);
            SongEssenceDTO dto = SongEssenceDTO.fromEntity(songEssence);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    /**
     * Busca canciones en Spotify según un término de búsqueda.
     *
     * @param query El término de búsqueda.
     * @return Una lista de canciones que coinciden con el término.
     */
    @GetMapping("/search")
    public ResponseEntity<Object> searchTracks(@RequestParam String query) {
        try {
            Object searchResults = spotifyService.searchTracks(query);
            return ResponseEntity.ok(searchResults);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar canciones: " + e.getMessage());
        }
    }
}
