package io.soulsong.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Profile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongEssence> favoriteSongs = new ArrayList<>();
    
    public Profile() {}
    
    public Profile(String john, String doe, LocalDate of, String mail) {
    
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<SongEssence> getFavoriteSongs() {
        return favoriteSongs;
    }
    
    public void addFavoriteSong(SongEssence song) {
        favoriteSongs.add(song);
    }
    
    public void setSongEssence(SongEssence song1) {
        favoriteSongs.add(song1);
    }
}
