package io.soulsong.mappers;

import io.soulsong.entities.User;
import io.soulsong.dtos.ProfileDTO;
import io.soulsong.dtos.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    
    private final ProfileMapper profileMapper;
    
    public UserMapper(ProfileMapper profileMapper) {
        this.profileMapper = profileMapper;
    }
    
    public UserDTO fromEntity(User user) {
        if (user == null) {
            return null;
        }
        
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setBirthday(user.getBirthday());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        
        if (user.getProfile() != null) {
            dto.setProfile(profileMapper.toDTO(user.getProfile())); // Usamos el mapper inyectado
        }
        
        return dto;
    }
    
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        
        User user = new User();
        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setBirthday(dto.getBirthday());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        
        if (dto.getProfile() != null) {
            user.setProfile(profileMapper.toEntity(dto.getProfile())); // Usamos el mapper inyectado
        }
        
        return user;
    }
    
    public List<UserDTO> fromEntities(List<User> users) {
        return users.stream()
              .map(this::fromEntity)
              .collect(Collectors.toList());
    }
    
    public List<User> toEntities(List<UserDTO> dtos) {
        return dtos.stream()
              .map(this::toEntity)
              .collect(Collectors.toList());
    }
}
