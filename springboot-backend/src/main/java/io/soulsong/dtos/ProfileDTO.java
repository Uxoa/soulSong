package io.soulsong.dtos;

import io.soulsong.entities.Profile;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileDTO {
    
    private Long id;
    private Long userId;
    private String userName;
    private String avatar;
    private List<SongDTO> songs;
    
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
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public List<SongDTO> getSongs() {
        return songs;
    }
    
    public void setFavoriteSongs(List<SongDTO> songs) {
        this.songs = songs;
    }
    
    
    /**
     * Convierte una entidad de tipo Profile a su representación DTO.
     *
     * @param profile Entidad Profile a convertir
     * @return ProfileDTO con los datos convertidos
     */
    public static ProfileDTO fromEntity(Profile profile) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setUserName(profile.getUserName());
        dto.setAvatar(profile.getAvatar());
        dto.setFavoriteSongs(
              profile.getSongs().stream()
                    .map(SongDTO::fromEntity) // Conversión correcta
                    .collect(Collectors.toList())
        );
        return dto;
    }
    
    public static ProfileDTO fromEntityWithoutUser(Profile profile) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setUserName(profile.getUserName());
        dto.setAvatar(profile.getAvatar());
        dto.setFavoriteSongs(
              profile.getSongs().stream()
                    .map(SongDTO::fromEntity) // Conversión correcta
                    .collect(Collectors.toList())
        );
        return dto;
    }
    
    
    /**
     * Convierte este DTO a su representación como entidad Profile.
     *
     * @return Entidad Profile
     */
    public static Profile toEntity(ProfileDTO profile) {
        Profile entity = new Profile();
        entity.setId(profile.getId());
        entity.setUserId(profile.getUserId());
        entity.setUserName(profile.getUserName());
        return entity;
    }
}
