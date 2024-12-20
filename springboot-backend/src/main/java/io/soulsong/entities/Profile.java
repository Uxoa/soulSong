package io.soulsong.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;

import java.util.List;

@Entity
public class Profile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "profile_image")
    private String profileImage; // Ruta o URL de la imagen de perfil
    
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("profile")
    private User user;
    
    @Column
    private String name;
    
    @Column
    private String email;
    
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongEssence> songEssences = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
          name = "favorite_songs", // Nombre de la tabla asociativa
          joinColumns = @JoinColumn(name = "profile_id"), // Clave foránea de Profile
          inverseJoinColumns = @JoinColumn(name = "song_essence_id") // Clave foránea de SongEssence
    )
    private List<SongEssence> favoriteSongs = new ArrayList<>();
    
    public Profile(String profileImage, User user, String name, String email) {
        this.profileImage = profileImage;
        this.user = user;
        this.name = name;
        this.email = email;
    }
    
    public Profile() {}
    
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
    
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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
}
