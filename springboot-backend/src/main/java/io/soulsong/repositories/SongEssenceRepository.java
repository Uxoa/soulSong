package io.soulsong.repositories;

import io.soulsong.entities.SongEssence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongEssenceRepository extends JpaRepository<SongEssence, String> {
}
