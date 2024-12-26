package io.soulsong.repositories;

import io.soulsong.entities.FavoriteSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteSongRepository extends JpaRepository<FavoriteSong, Long> {
}
