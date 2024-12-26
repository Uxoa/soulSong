package io.soulsong.services;

import io.soulsong.dtos.UserDTO;
import io.soulsong.entities.Profile;
import io.soulsong.entities.User;
import io.soulsong.mappers.UserMapper;
import io.soulsong.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
    }
    
    /**
     * Creates a new user with an associated empty profile.
     *
     * @param userDTO User data for creation.
     * @return The created UserDTO.
     */
    public UserDTO createUser(UserDTO userDTO) {
        validateUserInput(userDTO);
        
        // Check if email or username already exists
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email ya está registrado: " + userDTO.getEmail());
        }
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Nombre de usuario ya está registrado: " + userDTO.getUsername());
        }
        
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.fromEntity(user);
        // Save User with Profile
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
        
        // Update user fields
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        
        // Only update the password if provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword()); // Add password hashing here if necessary
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
    
    /**
     * Validates the user input.
     *
     * @param userDTO The user data to validate.
     */
    private void validateUserInput(UserDTO userDTO) {
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (userDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
    }
}
