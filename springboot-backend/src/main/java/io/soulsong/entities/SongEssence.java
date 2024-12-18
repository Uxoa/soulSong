// SongEssence.java
package io.soulsong.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "song_essences")
public class SongEssence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long trackId;
    private String mood;
    private double energy;
    private double danceability;
    private double tempo;
    private double acousticness;
    
    // Constructor vacío para JPA
    public SongEssence() {}
    
    // Constructor completo
    public SongEssence(Long trackId, String mood, double energy, double danceability, double tempo, double acousticness) {
        this.trackId = trackId;
        this.mood = mood;
        this.energy = energy;
        this.danceability = danceability;
        this.tempo = tempo;
        this.acousticness = acousticness;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getTrackId() {
        return trackId;
    }
    
    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }
    
    public String getMood() {
        return mood;
    }
    
    public void setMood(String mood) {
        this.mood = mood;
    }
    
    public double getEnergy() {
        return energy;
    }
    
    public void setEnergy(double energy) {
        this.energy = energy;
    }
    
    public double getDanceability() {
        return danceability;
    }
    
    public void setDanceability(double danceability) {
        this.danceability = danceability;
    }
    
    public double getTempo() {
        return tempo;
    }
    
    public void setTempo(double tempo) {
        this.tempo = tempo;
    }
    
    public double getAcousticness() {
        return acousticness;
    }
    
    public void setAcousticness(double acousticness) {
        this.acousticness = acousticness;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongEssence that = (SongEssence) o;
        return Objects.equals(trackId, that.trackId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(trackId);
    }
}
