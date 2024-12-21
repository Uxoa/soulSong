package io.soulsong.controllers;

import io.soulsong.dtos.UserDTO;
import io.soulsong.entities.Profile;
import io.soulsong.entities.User;
import io.soulsong.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    public ResponseEntity<UserDTO> createUserWithEmptyProfile(@RequestBody @Valid UserDTO userDTO) {
        // Create a new User entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        
        // Create an empty Profile
        Profile profile = new Profile();
        profile.setUserName(userDTO.getUsername() + "'s Profile");
        
        
        // Set the Profile on the User
        user.setProfile(profile);
        
        // Save the User (cascade will save the Profile)
        User savedUser = userService.createUser(userDTO).toEntity();
        
        // Convert to DTO for response
        UserDTO responseDTO = UserDTO.fromEntity(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO).toEntity();
        return ResponseEntity.ok(UserDTO.fromEntity(updatedUser));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
