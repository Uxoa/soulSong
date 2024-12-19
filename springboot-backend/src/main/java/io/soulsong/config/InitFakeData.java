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

import java.time.LocalDate;
import java.util.List;

@Configuration
public class InitFakeData {
    
    @Bean
    public CommandLineRunner initData(
          UserRepository userRepository,
          ProfileRepository profileRepository,
          SongEssenceRepository songEssenceRepository) {
        return args -> {
            User user1 = new User();
            user1.setUsername("johndoe");
            user1.setEmail("john.doe@example.com");
            
            User user2 = new User();
            user2.setUsername("janedoe");
            user2.setEmail("jane.doe@example.com");
            
            userRepository.saveAll(List.of(user1, user2));
            
            Profile profile1 = new Profile();
            profile1.setName("John");
            profile1.setEmail("john.doe@example.com");
            profile1.setUser(user1);
            
            Profile profile2 = new Profile();
            profile2.setName("Jane");
            profile2.setEmail("jane.doe@example.com");
            profile2.setUser(user2);
            
            profileRepository.saveAll(List.of(profile1, profile2));
        };
    }
}
