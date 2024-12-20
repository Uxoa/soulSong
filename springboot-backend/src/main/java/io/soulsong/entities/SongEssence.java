package io.soulsong.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SongEssence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cambiar a IDENTITY o AUTO
    private Long id;
    
    @Column
    private String songName;
    
    @ManyToMany(mappedBy = "favoriteSongs") // Cambiar 'favorite_songs' a 'favoriteSongs'
    private List<Profile> profiles = new ArrayList<>();
    
    private float danceability;
    private float energy;
    private float tempo;
    private float valence;
    
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    
    private Long trackId;
    
    public SongEssence() {}
    
    public SongEssence(Long trackId, String songName, float danceability, float energy, float tempo,
                       float valence) {
        this.trackId = trackId;
        this.songName = songName;
        this.danceability = danceability;
        this.energy = energy;
        this.tempo = tempo;
        this.valence = valence;
    }
    
    public SongEssence(String trackId, String name, float danceability, float energy, float tempo, float valence) {
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
    
    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }
    
    public Long getTrackId() {
        return trackId;
    }
}
