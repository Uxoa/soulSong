package io.soulsong.repositories;

import io.soulsong.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    
    /**
     * Encuentra un perfil por el ID del usuario relacionado.
     *
     * @param userId ID del usuario
     * @return Lista de perfiles asociados al usuario
     */
    @Query("SELECT p FROM Profile p WHERE p.user.id = :userId")
    List<Profile> findByUserId(Long userId);
    
    /**
     * Elimina un perfil asociado a un usuario por su ID.
     *
     * @param userId ID del usuario
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Profile p WHERE p.user.id = :userId")
    void deleteByUserId(Long userId);
}
