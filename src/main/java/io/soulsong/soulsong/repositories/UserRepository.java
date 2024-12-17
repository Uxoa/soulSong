package io.soulsong.soulsong.repositories;

import io.soulsong.soulsong.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
