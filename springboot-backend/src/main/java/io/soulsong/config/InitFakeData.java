package io.soulsong.config;

import io.soulsong.entities.FavoriteSong;
import io.soulsong.entities.Profile;
import io.soulsong.entities.SongEssence;
import io.soulsong.entities.User;
import io.soulsong.repositories.FavoriteSongRepository;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.SongEssenceRepository;
import io.soulsong.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class InitFakeData {
    
    @Bean
    public CommandLineRunner init(UserRepository userRepository,
                                  ProfileRepository profileRepository,
                                  SongEssenceRepository songEssenceRepository,
                                  FavoriteSongRepository favoriteSongRepository) {
        return args -> createTestData(userRepository, profileRepository, songEssenceRepository, favoriteSongRepository);
    }
    
    private void createTestData(UserRepository userRepository,
                                ProfileRepository profileRepository,
                                SongEssenceRepository songEssenceRepository,
                                FavoriteSongRepository favoriteSongRepository) {
        // Crear y guardar usuario
        User user1 = new User();
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setBirthday(LocalDate.of(1990, 1, 1));
        user1.setUsername("pericodelospalotes");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("securepassword");
        user1.setFirstname("John");
        user1.setLastname("Doe");
        
        // Guardar usuario y asegurar que está gestionado
        user1 = userRepository.save(user1);
        
        // Crear perfil y asociarlo al usuario gestionado
        Profile profile1 = new Profile();
        profile1.setUserName("pericodelospalotes's Profile");
        profile1.setAvatar("https://example.com/avatar/johndoe.png");
        profile1.setUser(user1); // Relación bidireccional
        
        profile1 = profileRepository.save(profile1); // Guardar perfil
        
        // Crear canciones
        SongEssence song1 = new SongEssence();
        song1.setSongName("Song 1");
        song1.setDanceability(0.5);
        song1.setEnergy(0.5);
        song1.setValence(0.5);
        song1.setTempo(120.0);
        song1.setTrackId("track1");
        
        SongEssence song2 = new SongEssence();
        song2.setSongName("Song 2");
        song2.setDanceability(0.6);
        song2.setEnergy(0.6);
        song2.setValence(0.6);
        song2.setTempo(130.0);
        song2.setTrackId("track2");
        
        // Guardar canciones
        songEssenceRepository.saveAll(Arrays.asList(song1, song2));
        
        // Crear favoritos y asociarlos al perfil
        FavoriteSong favoriteSong1 = new FavoriteSong();
        favoriteSong1.setProfile(profile1);
        favoriteSong1.setSongEssence(song1);
        favoriteSong1.setAddedDate(LocalDateTime.now());
        
        FavoriteSong favoriteSong2 = new FavoriteSong();
        favoriteSong2.setProfile(profile1);
        favoriteSong2.setSongEssence(song2);
        favoriteSong2.setAddedDate(LocalDateTime.now());
        
        // Guardar favoritos
        favoriteSongRepository.saveAll(Arrays.asList(favoriteSong1, favoriteSong2));
    }
}
