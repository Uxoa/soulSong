package io.soulsong.dtos;

// ImageProfileDTO
public class AvatarDTO {
    private Long id;
    private String mimeType;
    private String imageData; // Almacena como Base64
    
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
    
    public byte[] getImageData() {
        return imageData.getBytes();
    }
    
    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}