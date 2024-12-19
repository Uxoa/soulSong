package io.soulsong.mappers;

import io.soulsong.dtos.ImageProfileDTO;
import io.soulsong.entities.ImageProfile;
import org.springframework.stereotype.Component;

@Component
public class ImageProfileMapper {
    
    public ImageProfileDTO toDTO(ImageProfile imageProfile) {
        ImageProfileDTO dto = new ImageProfileDTO();
        dto.setId(imageProfile.getId());
        dto.setMimeType(imageProfile.getMimeType());
        dto.setImageData(imageProfile.getImageData());
        return dto;
    }
    
    public ImageProfile toEntity(ImageProfileDTO dto) {
        ImageProfile entity = new ImageProfile();
        entity.setMimeType(dto.getMimeType());
        entity.setImageData(dto.getImageData());
        return entity;
    }
}