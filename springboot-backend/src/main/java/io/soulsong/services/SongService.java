package io.soulsong.services;

import io.soulsong.entities.Song;
import io.soulsong.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    
    private final SongRepository songRepository;
    
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }
    
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
    
    public Song addFavorite(Song song) {
        return songRepository.save(song);
    }
    
    public void deleteFavorite(Long id) {
        songRepository.deleteById(id);
    }
}
