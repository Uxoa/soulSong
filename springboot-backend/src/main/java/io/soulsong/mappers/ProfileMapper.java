package io.soulsong.mappers;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.entities.Profile;

public class ProfileMapper {
      
      public ProfileMapper() {}
      
      public ProfileDTO toDTO(Profile profile) {
          ProfileDTO profileDTO = new ProfileDTO();
          profileDTO.setId(profile.getId());
          profileDTO.setAvatar(profile.getAvatar());
          profileDTO.setBirthday(profile.getBirthday());
          profileDTO.setCountry(profile.getCountry());
          profileDTO.setCity(profile.getCity());
          profileDTO.setCreatedAt(profile.getCreatedAt());
          profileDTO.setUpdatedAt(profile.getUpdatedAt());
          return profileDTO;
      }
      
      public Profile toEntity(ProfileDTO profileDTO) {
          Profile profile = new Profile();
          profile.setId(profileDTO.getId());
          profile.setAvatar(profileDTO.getAvatar());
          profile.setBirthday(profileDTO.getBirthday());
          profile.setCountry(profileDTO.getCountry());
          profile.setCity(profileDTO.getCity());
          profile.setCreatedAt(profileDTO.getCreatedAt());
          profile.setUpdatedAt(profileDTO.getUpdatedAt());
          return profile;
      }
}
