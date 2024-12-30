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
    private Double danceability;
    
    @Column(name = "energy")
    private Double energy;
    
    @Column(name = "tempo")
    private Double tempo;
    
    @Column(name = "valence")
    private Double valence;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    
    public SongEssence() {}
    
    public SongEssence(Long id, String trackId, String songName, Double danceability,
                       Double energy, Double tempo, Double valence,
                       String description, Profile profile) {
        this.id = id;
        this.trackId = trackId;
        this.songName = songName;
        this.danceability = danceability;
        this.energy = energy;
        this.tempo = tempo;
        this.valence = valence;
        this.description = description;
        this.profile = profile;
    }
    
    public SongEssence(Long id, double danceability, double energy, double valence, double tempo) {
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
    
    public double getDanceability() {
        return danceability;
    }
    
    public void setDanceability(Double danceability) {
        this.danceability = danceability;
    }
    
    public double getEnergy() {
        return energy;
    }
    
    public void setEnergy(Double energy) {
        this.energy = energy;
    }
    
    public double getTempo() {
        return tempo;
    }
    
    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }
    
    public double getValence() {
        return valence;
    }
    
    public void setValence(Double valence) {
        this.valence = valence;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Profile getProfile() {
        return profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
