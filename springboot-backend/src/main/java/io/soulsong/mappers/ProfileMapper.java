package io.soulsong.mappers;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.entities.Profile;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProfileMapper {
    
    private final SongMapper songMapper;
    
    public ProfileMapper(SongMapper songMapper) {
        this.songMapper = songMapper;
    }
    
    public Profile toEntity(ProfileDTO profileDTO) {
        if (profileDTO == null) {
            return null;
        }
        
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setUserId(profileDTO.getUserId());
        profile.setUserName(profileDTO.getUserName());
        profile.setAvatarUrl(profileDTO.getAvatarUrl());
        // Las relaciones complejas como `user` y `Songs` deben manejarse en el servicio
        return profile;
    }
    
    public ProfileDTO toDTO(Profile profile) {
        if (profile == null) {
            return null;
        }
        
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setUserId(profile.getUser() != null ? profile.getUser().getId() : null);
        profileDTO.setUserName(profile.getUserName());
        profileDTO.setAvatarUrl(profile.getAvatarUrl());
        profileDTO.setSongs(
              profile.getSongs().stream()
                    .map(songMapper::toDTO) // Usa la instancia inyectada de SongMapper
                    .collect(Collectors.toList())
        );
        return profileDTO;
    }
}
