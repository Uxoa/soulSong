package io.soulsong.dtos;

import io.soulsong.entities.FavoriteSong;
import java.time.LocalDateTime;

public class FavoriteSongDTO {
    
    private Long id;
    private Long profileId;
    private String songTitle;
    private SongEssenceDTO songEssence; // DTO para songEssence
    private LocalDateTime addedDate;
    private String trackId; // Campo trackId utilizado en el mapeo
    
    public FavoriteSongDTO() {}
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getProfileId() {
        return profileId;
    }
    
    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
    
    public String getSongTitle() {
        return songTitle;
    }
    
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
    
    public SongEssenceDTO getSongEssence() {
        return songEssence;
    }
    
    public void setSongEssence(SongEssenceDTO songEssence) {
        this.songEssence = songEssence;
    }
    
    public LocalDateTime getAddedDate() {
        return addedDate;
    }
    
    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }
    
    public String getTrackId() {
        return trackId;
    }
    
    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }
    
    /**
     * Convierte una entidad de tipo FavoriteSong a su representación DTO.
     *
     * @param favoriteSong Entidad FavoriteSong a convertir
     * @return FavoriteSongDTO con los datos convertidos
     */
    public static FavoriteSongDTO fromEntity(FavoriteSong favoriteSong) {
        FavoriteSongDTO dto = new FavoriteSongDTO();
        dto.setId(favoriteSong.getId());
        dto.setProfileId(favoriteSong.getProfile().getId());
        dto.setSongTitle(favoriteSong.getSongEssence().getSongName());
        dto.setSongEssence(SongEssenceDTO.fromEntity(favoriteSong.getSongEssence()));
        dto.setAddedDate(favoriteSong.getAddedDate());
        dto.setTrackId(favoriteSong.getSongEssence().getTrackId());
        return dto;
    }
}
