// src/main/java/io/soulsong/repository/ImageProfileRepository.java
package io.soulsong.repositories;

import io.soulsong.entities.ImageProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageProfileRepository extends JpaRepository<ImageProfile, Long> {
}
