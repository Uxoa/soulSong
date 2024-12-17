package io.soulsong.soulsong.services;

import io.soulsong.soulsong.entities.User;
import io.soulsong.soulsong.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
