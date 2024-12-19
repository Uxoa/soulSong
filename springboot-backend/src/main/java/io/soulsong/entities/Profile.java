package io.soulsong.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("profile")
    private List<SongEssence> favoriteSongs = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_profile_id", referencedColumnName = "id")
    private ImageProfile imageProfile;

    public Profile() {}

    public Profile(String john, String doe, LocalDate of, String mail) {}

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

    public void setFavoriteSongs(List<SongEssence> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
    }

    public ImageProfile getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(ImageProfile imageProfile) {
        this.imageProfile = imageProfile;
    }
}