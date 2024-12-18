package io.soulsong.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Map;

@Service
public class SpotifyAuthService {
    
    private static final Logger logger = LoggerFactory.getLogger(SpotifyAuthService.class);
    
    @Value("${spotify.client-id}")
    private String clientId;
    
    @Value("${spotify.client-secret}")
    private String clientSecret;
    
    @Value("${spotify.token-url}")
    private String tokenUrl;
    
    private String accessToken;
    private long tokenExpirationTime;
    
    public String getAccessToken() {
        if (accessToken == null || isTokenExpired()) {
            fetchAccessToken();
        }
        return accessToken;
    }
    
    private void fetchAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String auth = clientId + ":" + clientSecret;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            headers.set("Authorization", "Basic " + encodedAuth);
            
            String requestBody = "grant_type=client_credentials";
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            
            ResponseEntity<Map> response = restTemplate.exchange(
                  tokenUrl,
                  HttpMethod.POST,
                  request,
                  Map.class
            );
            
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("access_token")) {
                accessToken = (String) responseBody.get("access_token");
                int expiresIn = (Integer) responseBody.get("expires_in");
                tokenExpirationTime = System.currentTimeMillis() + (expiresIn * 1000L);
                logger.info("Token obtenido correctamente. Expirará en {} segundos.", expiresIn);
            } else {
                logger.error("Respuesta inesperada al obtener el token de Spotify: {}", responseBody);
                throw new RuntimeException("No se pudo obtener el token de Spotify");
            }
        } catch (Exception e) {
            logger.error("Error al obtener el token de Spotify: {}", e.getMessage());
            throw new RuntimeException("Error al obtener el token de Spotify", e);
        }
    }
    
    private boolean isTokenExpired() {
        return System.currentTimeMillis() >= tokenExpirationTime;
    }
}
