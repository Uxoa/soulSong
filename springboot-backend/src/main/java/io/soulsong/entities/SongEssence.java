package io.soulsong.entities;

import jakarta.persistence.*;

@Entity
public class SongEssence {
    
    @Id
    private String trackId;
    
    @Column(nullable = false)
    private String songName;
    
    private float danceability;
    private float energy;
    private float tempo;
    private float valence;
    
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
    
    public SongEssence() {}
    
    public SongEssence(String trackId, String songName, float danceability, float energy, float tempo, float valence) {
        this.trackId = trackId;
        this.songName = songName;
        this.danceability = danceability;
        this.energy = energy;
        this.tempo = tempo;
        this.valence = valence;
    }
    
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
    
    public Profile getProfile() {
        return profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
