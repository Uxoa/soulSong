package io.soulsong.services;

import io.soulsong.entities.FavoriteSong;
import io.soulsong.repositories.FavoriteSongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteSongService {
    
    private final FavoriteSongRepository favoriteSongRepository;
    
    public FavoriteSongService(FavoriteSongRepository favoriteSongRepository) {
        this.favoriteSongRepository = favoriteSongRepository;
    }
    
    public List<FavoriteSong> getAllFavorites() {
        return favoriteSongRepository.findAll();
    }
    
    public FavoriteSong addFavorite(FavoriteSong favoriteSong) {
        return favoriteSongRepository.save(favoriteSong);
    }
    
    public void deleteFavorite(Long id) {
        favoriteSongRepository.deleteById(id);
    }
}
