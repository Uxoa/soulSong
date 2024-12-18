// SongEssenceService.java
package io.soulsong.services;

import io.soulsong.entities.SongEssence;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SongEssenceService {
    
    private final SpotifyAuthService spotifyAuthService;
    private final RestTemplate restTemplate;
    
    public SongEssenceService(SpotifyAuthService spotifyAuthService) {
        this.spotifyAuthService = spotifyAuthService;
        this.restTemplate = new RestTemplate();
    }
    
    public SongEssence getSongEssence(Long trackId) {
        String token = spotifyAuthService.getAccessToken();
        
        String url = "https://api.spotify.com/v1/audio-features/" + trackId;
        
        // Configurar headers con el token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        
        // Llamar a la API de Spotify
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        
        // Extraer datos de la respuesta
        Map<String, Object> body = response.getBody();
        if (body != null) {
            String mood = "unknown"; // Ajustaremos cómo se obtiene después.
            double energy = (double) body.getOrDefault("energy", 0.0);
            double danceability = (double) body.getOrDefault("danceability", 0.0);
            double tempo = (double) body.getOrDefault("tempo", 0.0);
            double acousticness = (double) body.getOrDefault("acousticness", 0.0);
            
            return new SongEssence(trackId, mood, energy, danceability, tempo, acousticness);
        }
        
        throw new RuntimeException("No se pudo obtener la esencia de la canción.");
    }
}
