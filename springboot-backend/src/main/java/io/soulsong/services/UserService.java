package io.soulsong.services;

import io.soulsong.dtos.UserDTO;
import io.soulsong.entities.Profile;
import io.soulsong.entities.User;
import io.soulsong.repositories.UserRepository;
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
    
    /**
     * Creates a new user with an associated empty profile.
     *
     * @param userDTO User data for creation.
     * @return The created UserDTO.
     */
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        
        Profile profile = new Profile();
        profile.setUserName(userDTO.getUsername() + "'s Profile");
        
        user.setProfile(profile);
        
        User savedUser = userRepository.save(user);
        return UserDTO.fromEntity(savedUser);
    }
    
    /**
     * Retrieves all users as a list of DTOs.
     *
     * @return List of UserDTOs.
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
              .map(UserDTO::fromEntity)
              .collect(Collectors.toList());
    }
    
    /**
     * Retrieves a user by their ID.
     *
     * @param id The user's ID.
     * @return An optional UserDTO.
     */
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
              .map(UserDTO::fromEntity);
    }
    
    /**
     * Updates an existing user's information.
     *
     * @param id      The user's ID.
     * @param userDTO The updated user data.
     * @return The updated UserDTO.
     */
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        
        // Only update the password if it's provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword()); // Add hashing here if necessary
        }
        
        User updatedUser = userRepository.save(user);
        return UserDTO.fromEntity(updatedUser);
    }
    
    /**
     * Deletes a user by their ID.
     *
     * @param id The user's ID.
     */
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
