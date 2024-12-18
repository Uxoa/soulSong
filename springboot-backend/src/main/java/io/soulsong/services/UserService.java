package io.soulsong.services;

import io.soulsong.dtos.UserDTO;
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
    
    // Constructor para inyección de dependencias
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // Crear un usuario
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
              savedUser.getEmail()
        );
        
        return ResponseEntity.ok(userDTO);
    }
    
    // Obtener todos los usuarios
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        
        return users.stream()
              .map(user -> new UserDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail()
              ))
              .collect(Collectors.toList());
    }
    
    // Obtener un usuario por ID
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
              user.getEmail()
        );
        
        return ResponseEntity.ok(userDTO);
    }
    
    // Actualizar un usuario
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
              updatedUser.getEmail()
        );
        
        return ResponseEntity.ok(userDTO);
    }
    
    // Eliminar un usuario por ID
    public void deleteUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        
        userRepository.deleteById(id);
    }
}
