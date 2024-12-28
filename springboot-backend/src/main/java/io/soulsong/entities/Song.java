package io.soulsong.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Song {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
    
    @ManyToOne
    @JoinColumn(name = "song_essence_id", nullable = false)
    private SongEssence songEssence;
    
    private LocalDateTime addedDate;
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Profile getProfile() {
        return profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    public SongEssence getSongEssence() {
        return songEssence;
    }
    
    public void setSongEssence(SongEssence songEssence) {
        this.songEssence = songEssence;
    }
    
    public LocalDateTime getAddedDate() {
        return addedDate;
    }
    
    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }
}
