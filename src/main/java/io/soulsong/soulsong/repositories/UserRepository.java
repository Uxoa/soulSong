package io.soulsong.soulsong.repositories;

import io.soulsong.soulsong.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> id(Long id);
}
