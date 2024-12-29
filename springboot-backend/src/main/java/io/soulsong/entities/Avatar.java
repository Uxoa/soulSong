package io.soulsong.entities;

import jakarta.persistence.*;

@Entity
public class Avatar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String avatarUrl;
    
    @Lob
    @Column
    private String imageData;
    
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    
    public Avatar() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    
    public String getImageData() {
        return imageData;
        
    }
    
    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
    
    public Profile getProfile() {
        return profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
