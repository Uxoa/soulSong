package io.soulsong.services;

import io.soulsong.entities.SongEssence;
import io.soulsong.dtos.SpotifyDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SpotifyService {
    
    private final WebClient webClient;
    private final SpotifyAuthService spotifyAuthService;
    
    public SpotifyService(WebClient.Builder webClientBuilder, SpotifyAuthService spotifyAuthService) {
        this.webClient = webClientBuilder.baseUrl("https://api.spotify.com/v1").build();
        this.spotifyAuthService = spotifyAuthService;
    }
    
    /**
     * Obtener las características de audio de una canción por su ID.
     *
     * @param trackId El ID de la canción en Spotify.
     * @return Una instancia de SongEssence con las métricas de audio.
     */
    public SongEssence getAudioFeatures(String trackId) {
        String token = "Bearer " + spotifyAuthService.getAccessToken();
        SpotifyDTO.AudioFeatures audioFeatures = webClient
              .get()
              .uri("/audio-features/{id}", trackId)
              .header("Authorization", token)
              .retrieve()
              .bodyToMono(SpotifyDTO.AudioFeatures.class)
              .block();
        
        if (audioFeatures == null) {
            throw new RuntimeException("No se pudieron obtener las características de audio de la canción.");
        }
        
        return new SongEssence(
              trackId,
              audioFeatures.getName(),
              audioFeatures.getDanceability(),
              audioFeatures.getEnergy(),
              audioFeatures.getTempo(),
              audioFeatures.getValence()
        );
    }
    
    /**
     * Buscar canciones en Spotify.
     *
     * @param query El término de búsqueda.
     * @return Un SearchResult con los resultados de la búsqueda.
     */
    public SpotifyDTO.SearchResult searchTracks(String query) {
        String token = "Bearer " + spotifyAuthService.getAccessToken();
        return webClient
              .get()
              .uri(uriBuilder -> uriBuilder
                    .path("/search")
                    .queryParam("q", query)
                    .queryParam("type", "track")
                    .queryParam("limit", 10)
                    .build())
              .header("Authorization", token)
              .retrieve()
              .bodyToMono(SpotifyDTO.SearchResult.class)
              .block();
    }
}
