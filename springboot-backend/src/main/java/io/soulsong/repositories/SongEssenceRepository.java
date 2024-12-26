package io.soulsong.repositories;

import io.soulsong.entities.SongEssence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongEssenceRepository extends JpaRepository<SongEssence, Long> {
    List<SongEssence> findBySongName(String songName);
    List<SongEssence> findByDanceabilityGreaterThanEqual(Double danceability);
    List<SongEssence> findByEnergyGreaterThanEqual(Double energy);
    List<SongEssence> findByValenceGreaterThanEqual(Double valence);
    List<SongEssence> findByTempoGreaterThanEqual(Double tempo);
}
