package io.soulsong.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "song_essences")
public class SongEssence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "track_id")
    private String trackId;
    
    @Column(name = "song_name")
    private String songName;
    
    @Column(name = "danceability")
    private float danceability;
    
    @Column(name = "energy")
    private float energy;
    
    @Column(name = "tempo")
    private float tempo;
    
    @Column(name = "valence")
    private float valence;
    
    @ManyToOne
    @JoinColumn(name = "profile_id")
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
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
