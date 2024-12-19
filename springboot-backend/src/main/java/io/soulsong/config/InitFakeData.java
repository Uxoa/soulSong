package io.soulsong.config;

import io.soulsong.entities.Profile;
import io.soulsong.entities.SongEssence;
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
            // Crear canciones
            SongEssence song1 = new SongEssence("11dFghVXANMlKmJXsNCbNl", "Happy", 0.8f, 0.7f, 120.0f, 0.1f);
            SongEssence song2 = new SongEssence("113rghVXANMlKmJXsNCblp", "Melancholy", 0.6f, 0.5f, 100.0f, 0.3f);
            SongEssence song3 = new SongEssence("31dFghVXANMlKmJXsNCbTf", "Energetic", 0.9f, 0.8f, 140.0f, 0.05f);

            // Guardar canciones si no existen
            List<SongEssence> songs = List.of(song1, song2, song3);
            songs.forEach(song -> {
                if (!songEssenceRepository.existsById(song.getTrackId())) {
                    songEssenceRepository.save(song);
                    System.out.println("Saved song: " + song.getSongName());
                } else {
                    System.out.println("Song already exists: " + song.getSongName());
                }
            });

            // Crear perfiles
            Profile profile1 = new Profile("John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com");
            profile1.setSongEssence(song1);
            Profile profile2 = new Profile("Jane", "Doe", LocalDate.of(1995, 2, 2), "jane.doe@example.com");
            profile2.setSongEssence(song2);
        };
    }
}
