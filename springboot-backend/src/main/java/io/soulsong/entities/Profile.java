package io.soulsong.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Profile {
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "profile_song_essence", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "song_essence_id"))
    private List<SongEssence> favoriteSongs;
    
    public Profile() {
        this.favoriteSongs = new ArrayList<>();
    }
    
    public List<SongEssence> getFavoriteSongs() {
        return favoriteSongs;
    }
    
    public void addFavoriteSong(SongEssence song) {
        if (favoriteSongs.size() < 3) {
            favoriteSongs.add(song);
        } else {
            throw new IllegalStateException("Solo puedes agregar hasta 3 canciones favoritas.");
        }
    }
    
    public void removeFavoriteSong(SongEssence song) {
        favoriteSongs.remove(song);
    }
}
