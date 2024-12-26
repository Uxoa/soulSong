package io.soulsong.mappers;

import io.soulsong.dtos.AvatarDTO;
import io.soulsong.entities.Avatar;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class AvatarMapper {
    
    public AvatarDTO toDTO(Avatar avatar) {
        AvatarDTO dto = new AvatarDTO();
        dto.setId(avatar.getId());
        dto.setMimeType(avatar.getMimeType());
        dto.setImageData(avatar.getImageData() != null
              ? new String(avatar.getImageData().getBytes(), StandardCharsets.UTF_8) // Convert byte[] to String
              : null);
        return dto;
    }
    
    public Avatar toEntity(AvatarDTO dto) {
        Avatar avatar = new Avatar();
        avatar.setId(dto.getId());
        avatar.setMimeType(dto.getMimeType());
        avatar.setImageData(dto.getImageData() != null
              ? Arrays.toString(dto.getImageData().toString().getBytes(StandardCharsets.UTF_8)) // Convert String to byte[]
              : null);
          ;
        return avatar;
    }
}