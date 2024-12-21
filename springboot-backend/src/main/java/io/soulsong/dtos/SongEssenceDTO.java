package io.soulsong.dtos;

import io.soulsong.entities.SongEssence;

public class SongEssenceDTO {
    
    private Long id;
    private String songName; // Nombre de la canción
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSongName() {
        return songName;
    }
    
    public void setSongName(String songName) {
        this.songName = songName;
    }
    
    /**
     * Método para convertir una entidad SongEssence a un DTO.
     *
     * @param songEssence La entidad SongEssence
     * @return Una instancia de SongEssenceDTO
     */
    public static SongEssenceDTO fromEntity(SongEssence songEssence) {
        SongEssenceDTO dto = new SongEssenceDTO();
        dto.setId(songEssence.getId());
        dto.setSongName(songEssence.getSongName());
        return dto;
    }
    
    /**
     * Método para convertir un DTO a una entidad SongEssence.
     *
     * @return Una instancia de SongEssence
     */
    public SongEssence toEntity() {
        SongEssence songEssence = new SongEssence();
        songEssence.setId(this.id);
        songEssence.setSongName(this.songName);
        return songEssence;
    }
}
