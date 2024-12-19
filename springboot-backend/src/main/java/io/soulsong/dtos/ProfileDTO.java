package io.soulsong.dtos;

import java.util.List;

public class ProfileDTO {
    
    private Long id;
    private List<String> favoriteSongs;
    
    public ProfileDTO(Long id, List<String> favoriteSongs) {
        this.id = id;
        this.favoriteSongs = favoriteSongs;
    }
    
    public Long getId() {
        return id;
    }
    
    public List<String> getFavoriteSongs() {
        return favoriteSongs;
    }
}
