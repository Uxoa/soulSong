package io.soulsong.services;

import io.soulsong.dtos.SongEssenceDTO;
import io.soulsong.entities.SongEssence;
import io.soulsong.mappers.SongEssenceMapper;
import io.soulsong.repositories.SongEssenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongEssenceService {
    
    private final SongEssenceRepository songEssenceRepository;
    private final SongEssenceMapper songEssenceMapper;
    
    public SongEssenceService(SongEssenceRepository songEssenceRepository, SongEssenceMapper songEssenceMapper) {
        this.songEssenceRepository = songEssenceRepository;
        this.songEssenceMapper = songEssenceMapper;
    }
    
    public List<SongEssenceDTO> getAll() {
        return songEssenceRepository.findAll()
              .stream()
              .map(songEssenceMapper::toDTO)
              .collect(Collectors.toList());
    }
    
    public Optional<SongEssenceDTO> getById(Long id) {
        return songEssenceRepository.findById(id)
              .map(songEssenceMapper::toDTO);
    }
    
    public SongEssenceDTO createSongEssence(SongEssenceDTO songEssenceDTO) {
        SongEssence songEssence = songEssenceMapper.toEntity(songEssenceDTO);
        SongEssence saved = songEssenceRepository.save(songEssence);
        return songEssenceMapper.toDTO(saved);
    }
}
