package io.soulsong.dtos;

import io.soulsong.entities.SongEssence;

public class SongEssenceDTO {
    
    private Long id;
    private String songName; // Nombre de la canción
    private String trackId; // Identificador único de la canción (API externa)
    private Double danceability; // Característica de la canción
    private Double energy; // Característica de la canción
    private Double tempo; // Característica de la canción
    private Double valence; // Característica de la canción
    
    // Getters y Setters
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
    
    public String getTrackId() {
        return trackId;
    }
    
    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }
    
    public Double getDanceability() {
        return danceability;
    }
    
    public void setDanceability(Double danceability) {
        this.danceability = danceability;
    }
    
    public Double getEnergy() {
        return energy;
    }
    
    public void setEnergy(Double energy) {
        this.energy = energy;
    }
    
    public Double getTempo() {
        return tempo;
    }
    
    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }
    
    public Double getValence() {
        return valence;
    }
    
    public void setValence(Double valence) {
        this.valence = valence;
    }
    
    public static SongEssenceDTO fromEntity(SongEssence songEssence) {
        if (songEssence == null) return null;
        
        SongEssenceDTO dto = new SongEssenceDTO();
        dto.setId(songEssence.getId());
        dto.setSongName(songEssence.getSongName());
        dto.setTrackId(songEssence.getTrackId());
        dto.setDanceability(songEssence.getDanceability());
        dto.setEnergy(songEssence.getEnergy());
        dto.setTempo(songEssence.getTempo());
        dto.setValence(songEssence.getValence());
        return dto;
    }
    
    public SongEssence toEntity() {
        SongEssence songEssence = new SongEssence();
        songEssence.setId(this.id);
        songEssence.setSongName(this.songName);
        songEssence.setTrackId(this.trackId);
        songEssence.setDanceability(this.danceability);
        songEssence.setEnergy(this.energy);
        songEssence.setTempo(this.tempo);
        songEssence.setValence(this.valence);
        return songEssence;
    }
}
