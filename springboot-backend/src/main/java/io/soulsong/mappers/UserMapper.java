package io.soulsong.mappers;

import io.soulsong.entities.User;
import io.soulsong.dtos.ProfileDTO;
import io.soulsong.dtos.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    
    private static final ProfileMapper profileMapper = new ProfileMapper();
    
    public UserMapper() {
    }
    
    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setBirthday(user.getBirthday());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        if (user.getProfile() != null) {
            dto.setProfile(ProfileDTO.fromEntityWithoutUser(user.getProfile())); // Avoid cyclic reference
        }
        return dto;
    }
    
    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setBirthday(dto.getBirthday());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        if (dto.getProfile() != null) {
            user.setProfile(ProfileDTO.toEntity(dto.getProfile()));
        }
        return user;
    }
    
    public static List<UserDTO> fromEntities(List<User> users) {
        return users.stream().map(UserMapper::fromEntity).collect(Collectors.toList());
    }
    
    public static List<User> toEntities(List<UserDTO> dtos) {
        return dtos.stream().map(UserMapper::toEntity).collect(Collectors.toList());
    }
}
