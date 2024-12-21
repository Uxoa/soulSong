package io.soulsong.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "profile_image")
    private String profileImage; // Path or URL for the profile image
    
    @Column(name = "userName")
    private String userName;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonManagedReference
    @JsonBackReference
    private User user;
    
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongEssence> songEssences = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
          name = "favorite_songs", // Name of the join table
          joinColumns = @JoinColumn(name = "profile_id"), // Foreign key for Profile
          inverseJoinColumns = @JoinColumn(name = "song_essence_id") // Foreign key for SongEssence
    )
    private List<SongEssence> favoriteSongs = new ArrayList<>();
    
    public Profile() {}
    
    public Profile(String profileImage, User user) {
        this.profileImage = profileImage;
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProfileImage() {
        return profileImage;
    }
    
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<SongEssence> getSongEssences() {
        return songEssences;
    }
    
    public void addSongEssence(SongEssence songEssence) {
        songEssences.add(songEssence);
        songEssence.setProfile(this);
    }
    
    public void removeSongEssence(SongEssence songEssence) {
        songEssences.remove(songEssence);
        songEssence.setProfile(null);
    }
    
    public List<SongEssence> getFavoriteSongs() {
        return favoriteSongs;
    }
    
    public void addFavoriteSong(SongEssence songEssence) {
        favoriteSongs.add(songEssence);
    }
    
    public void removeFavoriteSong(SongEssence songEssence) {
        favoriteSongs.remove(songEssence);
    }
    
    @Override
    public String toString() {
        return "Profile{" +
              "id=" + id +
              ", profileImage='" + profileImage + '\'' +
              ", user=" + (user != null ? user.getId() : "null") +
              ", songEssences=" + songEssences +
              ", favoriteSongs=" + favoriteSongs +
              '}';
    }
    
    public String getAvatar() {
        return profileImage;
    }
    
    public void setAvatar(String avatar) {
        this.profileImage = avatar;
    }
    
    public String getBirthday() {
        return null;
    }
    
    public void setBirthday(String birthday) {
        // Do nothing
    }
    
    public String getCountry() {
        return null;
    }
    
    public void setCountry(String country) {
        // Do nothing
    }
    
    public String getCity() {
        return null;
    }
    
    public void setCity(String city) {
        // Do nothing
    }
    
    public String getCreatedAt() {
        return null;
    }
    
    public void setCreatedAt(String createdAt) {
        // Do nothing
    }
    
    public String getUpdatedAt() {
        return null;
    }
    
    public void setUpdatedAt(String updatedAt) {
        // Do nothing
    }
    
    public void setFavoriteSongs(List<SongEssence> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
    }
}
