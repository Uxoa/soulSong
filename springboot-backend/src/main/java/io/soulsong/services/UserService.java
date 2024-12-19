package io.soulsong.services;

import io.soulsong.dtos.ProfileDTO;
import io.soulsong.dtos.UserDTO;
import io.soulsong.entities.User;
import io.soulsong.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public ResponseEntity<UserDTO> createUser(User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(mapToUserDTO(savedUser));
    }
    
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
              .map(this::mapToUserDTO)
              .collect(Collectors.toList());
    }
    
    private UserDTO mapToUserDTO(User user) {
        ProfileDTO profileDTO = new ProfileDTO(
              user.getProfile().getId(),
              user.getProfile().getFavoriteSongs().stream()
                    .map(song -> song.getTrackId())
                    .collect(Collectors.toList())
        );
        
        return new UserDTO(
              user.getId(),
              user.getFirstName(),
              user.getLastName(),
              user.getEmail(),
              user.getPhoneNumber(),
              profileDTO
        );
    }
    
    public ResponseEntity<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
              .map(user -> ResponseEntity.ok(mapToUserDTO(user)))
              .orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<UserDTO> updateUser(Long id, User user) {
        return userRepository.findById(id)
              .map(existingUser -> {
                  existingUser.setFirstName(user.getFirstName());
                  existingUser.setLastName(user.getLastName());
                  existingUser.setEmail(user.getEmail());
                  existingUser.setPhoneNumber(user.getPhoneNumber());
                  existingUser.setProfile(user.getProfile());
                  User updatedUser = userRepository.save(existingUser);
                  return ResponseEntity.ok(mapToUserDTO(updatedUser));
              })
              .orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<UserDTO> deleteUser(Long id) {
        return userRepository.findById(id)
              .map(user -> {
                  userRepository.delete(user);
                  return ResponseEntity.ok(mapToUserDTO(user));
              })
              .orElse(ResponseEntity.notFound().build());
    }
}
