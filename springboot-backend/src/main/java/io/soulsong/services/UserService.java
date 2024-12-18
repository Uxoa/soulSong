package io.soulsong.services;

import io.soulsong.dtos.UserDTO;
import io.soulsong.entities.SongEssence;
import io.soulsong.entities.User;
import io.soulsong.repositories.UserRepository;
import io.soulsong.requests.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public ResponseEntity<UserDTO> createUser(UserRequest userRequest) {
        User user = new User(
              userRequest.firstName(),
              userRequest.lastName(),
              userRequest.birthday(),
              userRequest.email(),
              userRequest.phoneNumber()
        );
        
        User savedUser = userRepository.save(user);
        
        UserDTO userDTO = new UserDTO(
              savedUser.getId(),
              savedUser.getFirstName(),
              savedUser.getLastName(),
              savedUser.getEmail(),
              savedUser.getPhoneNumber(),
              savedUser.getProfile().getFavoriteSongs()
        );
        
        return ResponseEntity.ok(userDTO);
    }
    
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        
        return users.stream()
              .map(user -> new UserDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getProfile().getFavoriteSongs()
              ))
              .collect(Collectors.toList());
    }
    
    public ResponseEntity<UserDTO> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        User user = userOptional.get();
        UserDTO userDTO = new UserDTO(
              user.getId(),
              user.getFirstName(),
              user.getLastName(),
              user.getEmail(),
              user.getPhoneNumber(),
              user.getProfile().getFavoriteSongs()
        );
        
        return ResponseEntity.ok(userDTO);
    }
    
    public ResponseEntity<UserDTO> updateUser(Long id, UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findById(id);
        
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        User userToUpdate = userOptional.get();
        userToUpdate.setFirstName(userRequest.firstName());
        userToUpdate.setLastName(userRequest.lastName());
        userToUpdate.setBirthday(userRequest.birthday());
        userToUpdate.setEmail(userRequest.email());
        userToUpdate.setPhoneNumber(userRequest.phoneNumber());
        
        User updatedUser = userRepository.save(userToUpdate);
        
        UserDTO userDTO = new UserDTO(
              updatedUser.getId(),
              updatedUser.getFirstName(),
              updatedUser.getLastName(),
              updatedUser.getEmail(),
              updatedUser.getPhoneNumber(),
              updatedUser.getProfile().getFavoriteSongs()
        );
        
        return ResponseEntity.ok(userDTO);
    }
    
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        
        userRepository.deleteById(id);
    }
    
    public List<UserDTO> findSimilarUsers(Long userId) {
        User user = userRepository.findById(userId)
              .orElseThrow(() -> new RuntimeException("User not found"));
        
        List<SongEssence> favoriteSongs = user.getProfile().getFavoriteSongs();
        
        List<User> similarUsers = userRepository.findAll().stream()
              .filter(u -> !u.getId().equals(userId))
              .filter(u -> u.getProfile().getFavoriteSongs().stream()
                    .anyMatch(favoriteSongs::contains))
              .toList();
        
        return similarUsers.stream()
              .map(u -> new UserDTO(
                    u.getId(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getEmail(),
                    u.getPhoneNumber(),
                    u.getProfile().getFavoriteSongs()
              ))
              .toList();
    }
}
