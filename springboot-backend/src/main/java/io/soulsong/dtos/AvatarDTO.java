package io.soulsong.dtos;

// ImageProfileDTO
public class AvatarDTO {
    private Long id;
    private String avatarUrl;
    private String imageData; // Almacena como Base64
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public byte[] getImageData() {
        return imageData.getBytes();
    }
    
    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
    
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}