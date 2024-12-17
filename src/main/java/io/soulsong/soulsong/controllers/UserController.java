package io.soulsong.soulsong.controllers;

import io.soulsong.soulsong.entities.User;
import io.soulsong.soulsong.repositories.UserRepository;
import io.soulsong.soulsong.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    private final UserRepository userRepository;
    
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> userById = userRepository.findById(id);
        if (userById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userById.get());
    }
    
}
