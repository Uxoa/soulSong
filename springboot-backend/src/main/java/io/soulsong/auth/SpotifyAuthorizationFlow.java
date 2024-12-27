package io.soulsong.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SpotifyAuthorizationFlow {
    
    private final WebClient webClient;
    
    @Value("${spotify.client-id}")
    private String clientId;
    
    @Value("${spotify.client-secret}")
    private String clientSecret;
    
    @Value("${spotify.redirect-uri}")
    private String redirectUri;
    
    @Value("${spotify.refresh-token}")
    private String refreshToken;
    
    private String accessToken;
    
    public SpotifyAuthorizationFlow(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://accounts.spotify.com/api/token").build();
    }
    
    /**
     * Generar un nuevo access token usando el refresh token.
     *
     * @return El nuevo access token.
     */
    public String regenerateAccessToken() {
        try {
            TokenResponse response = webClient.post()
                  .uri(uriBuilder -> uriBuilder
                        .queryParam("grant_type", "refresh_token")
                        .queryParam("refresh_token", refreshToken)
                        .build())
                  .headers(headers -> headers.setBasicAuth(clientId, clientSecret))
                  .retrieve()
                  .bodyToMono(TokenResponse.class)
                  .block();
            
            if (response != null) {
                this.accessToken = response.getAccessToken();
                return accessToken;
            } else {
                throw new RuntimeException("No se pudo obtener un nuevo access token.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al regenerar el access token: " + e.getMessage());
        }
    }
    
    /**
     * Obtener el access token actual.
     *
     * @return El access token.
     */
    public String getAccessToken() {
        if (accessToken == null) {
            return regenerateAccessToken();
        }
        return accessToken;
    }
    
    // Clase interna para mapear la respuesta de Spotify
    private static class TokenResponse {
        private String accessToken;
        
        public String getAccessToken() {
            return accessToken;
        }
        
        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
