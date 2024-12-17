package io.soulsong.soulsong.services;

import io.soulsong.soulsong.entities.User;
import io.soulsong.soulsong.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public ResponseEntity<User> createUser(User user){
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
    
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }
    
    public void deleteUserById(Long id){
        Optional<User> userToDelete = userRepository.findById(id);
        if(userToDelete.isEmpty()){
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
    
    public ResponseEntity<User> updateUserById(Long id, User updateUserData) {
        // Buscar el usuario en la base de datos por ID
        Optional<User> userOptional = userRepository.findById(id);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        
        // Obtener el usuario existente y actualizar sus campos
        User userToUpdate = userOptional.get();
        userToUpdate.setFirstName(updateUserData.getFirstName());
        userToUpdate.setLastName(updateUserData.getLastName());
        userToUpdate.setBirthday(updateUserData.getBirthday());
        userToUpdate.setEmail(updateUserData.getEmail());
        userToUpdate.setPhoneNumber(updateUserData.getPhoneNumber());
        
        // Guardar el usuario actualizado
        User updatedUser = userRepository.save(userToUpdate);
        
        // Retornar el usuario actualizado como respuesta
        return ResponseEntity.ok(updatedUser);
    }
    
}

