package io.soulsong.controllers;

import io.soulsong.dtos.UserDTO;
import io.soulsong.requests.UserRequest;
import io.soulsong.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    
    // Constructor para inyección de dependencias
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    // Crear un usuario
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
    
    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
    
    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    // Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }
    
    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    
    
    // Buscar usuarios con gustos musicales afines
    @GetMapping("/{userId}/similar")
    public ResponseEntity<List<UserDTO>> getSimilarUsers(@PathVariable Long userId) {
        List<UserDTO> similarUsers = userService.findSimilarUsers(userId);
        return ResponseEntity.ok(similarUsers);
    }
}
