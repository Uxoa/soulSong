package io.soulsong.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "song_essence") // Nombre de la tabla en la base de datos
public class SongEssence {
    
    @Id
    private String trackId; // ID único de la canción
    
    private String songName;
    private float danceability;
    private float energy;
    private float tempo;
    private float valence;
    
    // Constructor vacío requerido por JPA
    public SongEssence() {
    }
    
    // Constructor con parámetros
    public SongEssence(String trackId, String songName, float danceability, float energy, float tempo, float valence) {
        this.trackId = trackId;
        this.songName = songName;
        this.danceability = danceability;
        this.energy = energy;
        this.tempo = tempo;
        this.valence = valence;
    }
    
    // Getters y setters
    public String getTrackId() {
        return trackId;
    }
    
    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }
    
    public String getSongName() {
        return songName;
    }
    
    public void setSongName(String songName) {
        this.songName = songName;
    }
    
    public float getDanceability() {
        return danceability;
    }
    
    public void setDanceability(float danceability) {
        this.danceability = danceability;
    }
    
    public float getEnergy() {
        return energy;
    }
    
    public void setEnergy(float energy) {
        this.energy = energy;
    }
    
    public float getTempo() {
        return tempo;
    }
    
    public void setTempo(float tempo) {
        this.tempo = tempo;
    }
    
    public float getValence() {
        return valence;
    }
    
    public void setValence(float valence) {
        this.valence = valence;
    }
}
