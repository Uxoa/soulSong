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
    public CommandLineRunner initData(
          UserRepository userRepository,
          ProfileRepository profileRepository,
          SongEssenceRepository songEssenceRepository) {
        
        return args -> {
            // Crear usuarios
            User user = new User();
            user.setUsername("testuser");
            user.setEmail("testuser@example.com");
            user.setPassword("securepassword");
            userRepository.save(user);
            
            User user1 = new User();
            user1.setUsername("john.doe");
            user1.setEmail("john.doe@example.com");
            user1 = userRepository.save(user1);
            
            User user2 = new User();
            user2.setUsername("jane.williams");
            user2.setEmail("jane.doe@example.com");
            user2 = userRepository.save(user2);
            
            // Crear perfiles
            Profile profile1 = new Profile();
            profile1.setName("John's Profile");
            profile1.setEmail(user1.getEmail());
            profile1.setUser(user1);
            profileRepository.save(profile1);
            
            Profile profile2 = new Profile();
            profile2.setName("Jane's Profile");
            profile2.setEmail(user2.getEmail());
            profile2.setUser(user2);
            profileRepository.save(profile2);
            
            // Crear canciones
            SongEssence songEssence1 = new SongEssence();
            songEssence1.setSongName("Song 1");
            songEssence1.setDanceability(0.5F);
            songEssence1.setEnergy(0.5F);
            songEssence1.setValence(0.5F);
            songEssence1.setTempo(120.0F);
            songEssenceRepository.save(songEssence1);
        };
    }
}
