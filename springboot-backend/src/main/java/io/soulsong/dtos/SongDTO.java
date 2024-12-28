package io.soulsong.dtos;

import io.soulsong.entities.Song;

import java.time.LocalDateTime;

public class SongDTO {
    
    private Long id;
    private Long profileId;
    private String songTitle;
    private SongEssenceDTO songEssence; // DTO para songEssence
    private LocalDateTime addedDate;
    private String trackId; // Campo trackId utilizado en el mapeo
    
    public SongDTO() {}
    
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
     * @param song Entidad FavoriteSong a convertir
     * @return SongDTO con los datos convertidos
     */
    public static SongDTO fromEntity(Song song) {
        SongDTO dto = new SongDTO();
        dto.setId(song.getId());
        dto.setProfileId(song.getProfile().getId());
        dto.setSongTitle(song.getSongEssence().getSongName());
        dto.setSongEssence(SongEssenceDTO.fromEntity(song.getSongEssence()));
        dto.setAddedDate(song.getAddedDate());
        dto.setTrackId(song.getSongEssence().getTrackId());
        return dto;
    }
}
