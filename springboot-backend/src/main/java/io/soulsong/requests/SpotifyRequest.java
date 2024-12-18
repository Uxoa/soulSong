package io.soulsong.requests;

import io.soulsong.entities.SongEssence;
import io.soulsong.dtos.SpotifyDTO;
import io.soulsong.mappers.SongEssenceMapper;
import io.soulsong.services.SpotifyAuthService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyRequest {
    
    private final SpotifyAuthService spotifyAuthService;
    private final RestTemplate restTemplate;
    
    public SpotifyRequest(SpotifyAuthService spotifyAuthService) {
        this.spotifyAuthService = spotifyAuthService;
        this.restTemplate = new RestTemplate();
    }
    
    public SongEssence getSongEssence(String trackId) {
        String token = spotifyAuthService.getAccessToken();
        
        String url = "https://api.spotify.com/v1/audio-features/" + trackId;
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        
        ResponseEntity<SpotifyDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, SpotifyDTO.class);
        
        SpotifyDTO body = response.getBody();
        if (body != null) {
            return SongEssenceMapper.mapToSongEssence(body);
        }
        
        throw new RuntimeException("No se pudo obtener la esencia de la canción.");
    }
}
