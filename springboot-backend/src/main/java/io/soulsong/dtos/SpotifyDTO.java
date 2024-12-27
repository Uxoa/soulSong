package io.soulsong.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SpotifyDTO {
    
    public static class UserProfile {
        private String id;
        private String displayName;
        private String email;
        
        @JsonProperty("id")
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        @JsonProperty("display_name")
        public String getDisplayName() {
            return displayName;
        }
        
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
        
        @JsonProperty("email")
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
    }
    
    public static class SearchResult {
        @JsonProperty("tracks")
        private Tracks tracks;
        
        public Tracks getTracks() {
            return tracks;
        }
        
        public void setTracks(Tracks tracks) {
            this.tracks = tracks;
        }
    }
    
    public static class Tracks {
        @JsonProperty("items")
        private List<Track> items;
        
        public List<Track> getItems() {
            return items;
        }
        
        public void setItems(List<Track> items) {
            this.items = items;
        }
    }
    
    public static class Track {
        private String id;
        private String name;
        
        @JsonProperty("id")
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        @JsonProperty("name")
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
    }
    
    public static class AudioFeatures {
        private Long id;
        private String name;
        private float danceability;
        private float energy;
        private float tempo;
        private float valence;
        
        @JsonProperty("id")
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        @JsonProperty("name")
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        @JsonProperty("danceability")
        public double getDanceability() {
            return danceability;
        }
        
        public void setDanceability(float danceability) {
            this.danceability = danceability;
        }
        
        @JsonProperty("energy")
        public double getEnergy() {
            return energy;
        }
        
        public void setEnergy(float energy) {
            this.energy = energy;
        }
        
        @JsonProperty("tempo")
        public double getTempo() {
            return tempo;
        }
        
        public void setTempo(float tempo) {
            this.tempo = tempo;
        }
        
        @JsonProperty("valence")
        public double getValence() {
            return valence;
        }
        
        public void setValence(float valence) {
            this.valence = valence;
        }
    }
}
