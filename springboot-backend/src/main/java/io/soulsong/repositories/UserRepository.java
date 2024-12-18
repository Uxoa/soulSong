package io.soulsong.repositories;

import io.soulsong.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Este método personalizado sigue las convenciones de JPA para buscar por ID
    Optional<User> findById(Long id);
}
