package io.soulsong.soulsong.services;

import io.soulsong.soulsong.dtos.UserDTO;
import io.soulsong.soulsong.entities.User;
import io.soulsong.soulsong.repositories.UserRepository;
import io.soulsong.soulsong.requests.UserRequest;
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
        
        // Convertir a UserDTO y retornar
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
}
