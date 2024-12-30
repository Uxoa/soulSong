package io.soulsong.services;

import io.soulsong.entities.SongEssence;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SongEssenceService {
    
    private final RestTemplate restTemplate;
    private final io.soulsong.services.FakerService fakerService;
    
    public SongEssenceService(RestTemplate restTemplate, io.soulsong.services.FakerService fakerService) {
        this.restTemplate = restTemplate;
        this.fakerService = fakerService;
    }
    
    /**
     * Genera atributos simulados y los procesa con IA para crear una descripción.
     *
     * @param trackId El ID de la canción.
     * @return Un objeto Map con atributos y descripción.
     */
    public Map<String, Object> analyzeSongWithAI(String trackId) {
        // Paso 1: Obtener atributos simulados
        SongEssence songEssence = fakerService.generateFakeSongEssence(trackId);
        
        // Paso 2: Generar descripción usando IA
        String description = generateDescriptionWithAI(songEssence);
        
        // Retornar atributos y descripción
        Map<String, Object> result = new HashMap<>();
        result.put("songEssence", songEssence);
        result.put("description", description);
        return result;
    }
    
    /**
     * Genera una descripción en lenguaje natural usando IA.
     *
     * @param songEssence Atributos de la canción.
     * @return Descripción generada.
     */
    private String generateDescriptionWithAI(SongEssence songEssence) {
        String aiApiUrl = "https://api.openai.com/v1/completions";
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", buildAIPrompt(songEssence));
        requestBody.put("max_tokens", 50);
        requestBody.put("temperature", 0.7);
        requestBody.put("model", "text-davinci-003");
        
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer YOUR_OPENAI_API_KEY");
        headers.put("Content-Type", "application/json");
        
        ResponseEntity<Map> response = restTemplate.postForEntity(aiApiUrl, requestBody, Map.class);
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Map<String, Object> data = response.getBody();
            return (String) ((Map<String, Object>) ((List<?>) data.get("choices")).get(0)).get("text");
        } else {
            throw new RuntimeException("Error al generar descripción con IA: " + response.getStatusCode());
        }
    }
    
    /**
     * Crea un prompt para la IA basado en SongEssence.
     *
     * @param songEssence Atributos de la canción.
     * @return Prompt en lenguaje natural.
     */
    private String buildAIPrompt(SongEssence songEssence) {
        return String.format(
              "Genera una breve descripción de una canción con las siguientes características: " +
                    "tempo %.1f, danceability %.2f, energy %.2f y valence %.2f.",
              songEssence.getTempo(),
              songEssence.getDanceability(),
              songEssence.getEnergy(),
              songEssence.getValence()
        );
    }
    
    /**
     * Analiza una descripción generada por IA y la guarda en la entidad SongEssence.
     *
     * @param songEssence La entidad SongEssence a actualizar.
     * @return La descripción generada.
     */
    public String analyzeDescription(SongEssence songEssence) {
        String description = generateDescriptionWithAI(songEssence);
        songEssence.setDescription(description);
        return description;
        
    }
    
  
}
