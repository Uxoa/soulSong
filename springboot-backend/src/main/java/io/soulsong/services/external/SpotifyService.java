package io.soulsong.services.external;

import io.soulsong.entities.SongEssence;
import io.soulsong.dtos.SpotifyDTO;
import io.soulsong.services.SpotifyAuthService;
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
     * Obtener una canción por su ID.
     *
     * @param trackId El ID de la canción en Spotify.
     * @return Una instancia de SongEssence con la información de la canción.
     */
    public SongEssence getSongByTrackId(String trackId) {
        SpotifyDTO.Track track = fetchTrack(trackId);
        return getAudioFeatures(track.getId());
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
            throw new RuntimeException("No se pudieron obtener las características de audio para el track ID: " + trackId);
        }
        
        return new SongEssence(
              audioFeatures.getId(),
              audioFeatures.getDanceability(),
              audioFeatures.getEnergy(),
              audioFeatures.getValence(),
              audioFeatures.getTempo()
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
        SpotifyDTO.SearchResult searchResult = webClient
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
        
        if (searchResult == null || searchResult.getTracks().getItems().isEmpty()) {
            throw new RuntimeException("No se encontraron resultados para la búsqueda: " + query);
        }
        
        return searchResult;
    }
    
    /**
     * Obtener una canción por su nombre.
     *
     * @param songName El nombre de la canción.
     * @return Una instancia de SongEssence con los detalles de la canción.
     */
    public SongEssence getSongByName(String songName) {
        SpotifyDTO.SearchResult searchResult = searchTracks(songName);
        
        if (searchResult.getTracks().getItems().isEmpty()) {
            throw new RuntimeException("No se encontraron canciones con el nombre: " + songName);
        }
        
        SpotifyDTO.Track track = searchResult.getTracks().getItems().get(0);
        return getAudioFeatures(track.getId());
    }
    
    /**
     * Fetch the track details by trackId.
     *
     * @param trackId El ID del track.
     * @return Una instancia de Track con los detalles.
     */
    private SpotifyDTO.Track fetchTrack(String trackId) {
        String token = "Bearer " + spotifyAuthService.getAccessToken();
        SpotifyDTO.Track track = webClient
              .get()
              .uri("/tracks/{id}", trackId)
              .header("Authorization", token)
              .retrieve()
              .bodyToMono(SpotifyDTO.Track.class)
              .block();
        
        if (track == null) {
            throw new RuntimeException("No se pudieron obtener detalles del track con ID: " + trackId);
        }
        
        return track;
    }
}
