package io.soulsong.entities;

import jakarta.persistence.*;

@Entity
public class ImageProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String mimeType;
    
    @Lob
    @Column
    private String imageData;
    
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    
    public ImageProfile() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    public String getMimeType() {
        return mimeType;
    }
    
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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
