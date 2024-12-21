package io.soulsong.repositories;

import io.soulsong.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find a user by username (useful for login or identification)
    Optional<User> findByUsername(String username);
    
    // Find a user by email (useful for login or notifications)
    Optional<User> findByEmail(String email);
    
    // Check if a username already exists (useful for registration validation)
    boolean existsByUsername(String username);
    
    // Check if an email already exists (useful for registration validation)
    boolean existsByEmail(String email);
}
