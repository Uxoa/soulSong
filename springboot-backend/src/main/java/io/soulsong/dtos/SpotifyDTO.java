package io.soulsong.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SpotifyDTO {
    
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
        private String id;
        private String name;
        private float danceability;
        private float energy;
        private float tempo;
        private float valence;
        
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
        
        @JsonProperty("danceability")
        public float getDanceability() {
            return danceability;
        }
        
        public void setDanceability(float danceability) {
            this.danceability = danceability;
        }
        
        @JsonProperty("energy")
        public float getEnergy() {
            return energy;
        }
        
        public void setEnergy(float energy) {
            this.energy = energy;
        }
        
        @JsonProperty("tempo")
        public float getTempo() {
            return tempo;
        }
        
        public void setTempo(float tempo) {
            this.tempo = tempo;
        }
        
        @JsonProperty("valence")
        public float getValence() {
            return valence;
        }
        
        public void setValence(float valence) {
            this.valence = valence;
        }
    }
}
