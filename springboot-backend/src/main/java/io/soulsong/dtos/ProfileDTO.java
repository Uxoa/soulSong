package io.soulsong.dtos;

import io.soulsong.entities.Profile;
import io.soulsong.entities.SongEssence;

import java.util.List;

public class ProfileDTO {
    
    private Long id;
    private Long userId;
    private String name;
    private String email;
    private List<String> favoriteSongIds;
    
    public ProfileDTO() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
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
    
    public List<String> getFavoriteSongIds() {
        return favoriteSongIds;
    }
    
    public void setFavoriteSongIds(List<String> favoriteSongIds) {
        this.favoriteSongIds = favoriteSongIds;
    }
    
    public static ProfileDTO fromEntity(Profile profile) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setName(profile.getName());
        dto.setEmail(profile.getEmail());
        dto.setFavoriteSongIds(
              profile.getFavoriteSongs().stream().map(SongEssence::getTrackId).toList()
        );
        return dto;
    }
}
