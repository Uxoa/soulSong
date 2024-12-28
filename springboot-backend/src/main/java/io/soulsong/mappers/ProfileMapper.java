package io.soulsong.mappers;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.entities.Profile;

import java.util.stream.Collectors;

public class ProfileMapper {
    
    public static Profile toEntity(ProfileDTO profileDTO) {
        if (profileDTO == null) {
            return null;
        }
        
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setUserId(profileDTO.getUserId());
        profile.setUserName(profileDTO.getUserName());
        profile.setAvatar(profileDTO.getAvatar());
        // Las relaciones complejas como `user` y `favoriteSongs` deben manejarse en el servicio
        return profile;
    }
    
    public static ProfileDTO toDTO(Profile profile) {
        if (profile == null) {
            return null;
        }
        
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setUserId(profile.getUser().getId());
        profileDTO.setUserName(profile.getUserName());
        profileDTO.setAvatar(profile.getAvatar());
        profileDTO.setFavoriteSongs(
              profile.getSongs().stream()
                    .map(SongMapper::toDTO)
                    .collect(Collectors.toList())
        );
        profileDTO.setUserId(profile.getUser() != null ? profile.getUser().getId() : null);
        return profileDTO;
    }
}
