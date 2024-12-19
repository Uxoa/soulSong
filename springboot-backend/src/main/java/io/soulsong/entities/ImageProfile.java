// src/main/java/io/soulsong/model/ImageProfile.java
package io.soulsong.entities;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "image_profiles")
public class ImageProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Lob
    @Column(nullable = false)
    private byte[] imageData;
    
    @Column(nullable = false)
    private String mimeType;
    
    @OneToOne(mappedBy = "imageProfile")
    private Profile profile;
    
    public ImageProfile(byte[] imageData, String mimeType) {
        this.imageData = imageData;
        this.mimeType = mimeType;
    }
    
    public ImageProfile(Long id, byte[] imageData, String mimeType) {
        this.id = id;
        this.imageData = imageData;
        this.mimeType = mimeType;
    }
    
    public ImageProfile(byte[] imageData, String mimeType, Profile profile) {
        this.imageData = imageData;
        this.mimeType = mimeType;
        this.profile = profile;
    }
    
    public ImageProfile(Long id, byte[] imageData, String mimeType, Profile profile) {
        this.id = id;
        this.imageData = imageData;
        this.mimeType = mimeType;
        this.profile = profile;
    }
    
    public ImageProfile() {
    
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getImageData() {
        return Arrays.toString(imageData);
    }
    
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    
    public String getMimeType() {
        return mimeType;
    }
    
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
    public Profile getProfile() {
        return profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
