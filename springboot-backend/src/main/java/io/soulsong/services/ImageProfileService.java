// src/main/java/io/soulsong/service/ImageProfileService.java
package io.soulsong.services;

import io.soulsong.dtos.ImageProfileDTO;
import io.soulsong.dtos.ImageProfileDTO;
import io.soulsong.dtos.ProfileDTO;
import io.soulsong.mappers.ImageProfileMapper;
import io.soulsong.entities.ImageProfile;
import io.soulsong.repositories.ImageProfileRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageProfileService {
    private final ImageProfileRepository repository;
    private final ImageProfileMapper mapper;
    
    public ImageProfileService(ImageProfileRepository repository, ImageProfileMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    public ImageProfileDTO saveImageProfile(ImageProfileDTO dto) {
        ImageProfile entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }
    
    public Optional<ImageProfileDTO> getImageProfile(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }
    
    public void deleteImageProfile(Long id) {
        repository.deleteById(id);
    }
    
    
    public ImageProfileDTO uploadImage(Long id, @Valid ImageProfileDTO imageProfileDTO) {
        ImageProfileDTO savedImage = saveImageProfile(imageProfileDTO);
        return savedImage;
    }
    
    public Optional<Object> getImage(Long id) {
        return Optional.ofNullable(getImageProfile(id));
    }
    
    public void deleteImage(Long id) {
        deleteImageProfile(id);
    }
}
