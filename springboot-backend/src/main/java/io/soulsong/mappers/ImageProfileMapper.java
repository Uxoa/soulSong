package io.soulsong.mappers;

import io.soulsong.dtos.ImageProfileDTO;
import io.soulsong.entities.ImageProfile;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class ImageProfileMapper {
    
    public ImageProfileDTO toDTO(ImageProfile imageProfile) {
        ImageProfileDTO dto = new ImageProfileDTO();
        dto.setId(imageProfile.getId());
        dto.setMimeType(imageProfile.getMimeType());
        dto.setImageData(imageProfile.getImageData() != null
              ? new String(imageProfile.getImageData().getBytes(), StandardCharsets.UTF_8) // Convert byte[] to String
              : null);
        return dto;
    }
    
    public ImageProfile toEntity(ImageProfileDTO dto) {
        ImageProfile imageProfile = new ImageProfile();
        imageProfile.setId(dto.getId());
        imageProfile.setMimeType(dto.getMimeType());
        imageProfile.setImageData(dto.getImageData() != null
              ? Arrays.toString(dto.getImageData().toString().getBytes(StandardCharsets.UTF_8)) // Convert String to byte[]
              : null);
          ;
        return imageProfile;
    }
}