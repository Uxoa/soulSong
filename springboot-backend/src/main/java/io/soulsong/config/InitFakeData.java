package io.soulsong.config;

import io.soulsong.entities.Profile;
import io.soulsong.entities.SongEssence;
import io.soulsong.entities.User;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.SongEssenceRepository;
import io.soulsong.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class InitFakeData {
    
    @Bean
    public CommandLineRunner initData(UserRepository userRepository, ProfileRepository profileRepository, SongEssenceRepository songEssenceRepository) {
        return (args) -> {
            createTestData(userRepository, profileRepository, songEssenceRepository);
        };
    }

    public void createTestData(UserRepository userRepository, ProfileRepository profileRepository, SongEssenceRepository songEssenceRepository) {
        // Crear usuario
        User user1 = new User();
        user1.setUsername("john.doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("securepassword");
        user1 = userRepository.save(user1); // Asegurar que User esté gestionado
        
        // Crear perfil y asociarlo al usuario
        Profile profile1 = new Profile();
        profile1.setUserName("John's Profile");
        profile1.setUser(user1);
        
        // Crear canciones y asignarlas al perfil
        SongEssence songEssence1 = new SongEssence();
        songEssence1.setSongName("Song 1");
        songEssence1.setDanceability(0.5F);
        songEssence1.setEnergy(0.5F);
        songEssence1.setValence(0.5F);
        songEssence1.setTempo(120.0F);
        songEssence1.setProfile(profile1);
     
        
        SongEssence songEssence2 = new SongEssence();
        songEssence2.setSongName("Song 2");
        songEssence2.setDanceability(0.6F);
        songEssence2.setEnergy(0.6F);
        songEssence2.setValence(0.6F);
        songEssence2.setTempo(130.0F);
        songEssence2.setProfile(profile1);
  
        
        // Añadir canciones a las favoritas del perfil
        profile1.getFavoriteSongs().add(songEssence1);
        profile1.getFavoriteSongs().add(songEssence2);
        
        // Guardar el perfil con las canciones favoritas
        userRepository.save(user1);
    }
}
