package io.soulsong.soulsong.controllers;

import io.soulsong.soulsong.entities.User;
import io.soulsong.soulsong.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
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
    
    
}
