package io.soulsong.config;

import com.github.javafaker.Faker;
import io.soulsong.entities.Profile;
import io.soulsong.entities.Song;
import io.soulsong.entities.SongEssence;
import io.soulsong.entities.User;
import io.soulsong.repositories.SongRepository;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.SongEssenceRepository;
import io.soulsong.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
public class InitFakeData {
    
    private static final Logger logger = LoggerFactory.getLogger(InitFakeData.class);
    
    @Bean
    public CommandLineRunner init(UserRepository userRepository,
                                  ProfileRepository profileRepository,
                                  SongEssenceRepository songEssenceRepository,
                                  SongRepository songRepository) {
        return args -> createTestData(userRepository, profileRepository, songEssenceRepository, songRepository);
    }
    
    private void createTestData(UserRepository userRepository,
                                ProfileRepository profileRepository,
                                SongEssenceRepository songEssenceRepository,
                                SongRepository songRepository) {
        Faker faker = new Faker(new Locale("en-US"));
        
        // Create random song essences
        List<SongEssence> songEssences = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            SongEssence songEssence = createSongEssence(
                  faker.rockBand().name(),
                  faker.internet().uuid(),
                  faker.number().randomDouble(2, 0, 1),  // Random danceability [0-1]
                  faker.number().randomDouble(2, 0, 1),  // Random energy [0-1]
                  faker.number().randomDouble(2, 0, 1),  // Random valence [0-1]
                  faker.number().numberBetween(80, 200)  // Random tempo [80-200]
            );
            songEssences.add(songEssence);
        }
        songEssenceRepository.saveAll(songEssences);
        logger.info("Canciones generadas: {}", songEssences);
        
        // Create random users, profiles, and their songs
        for (int i = 1; i <= 10; i++) {
            User user = createUser(
                  faker.name().firstName(),
                  faker.name().lastName(),
                  faker.internet().emailAddress(),
                  faker.name().username(),
                  faker.date().birthday(18, 60).toInstant().atZone(ZoneId.systemDefault()).toLocalDate() // Random birthday
            );
            user = userRepository.save(user);
            logger.info("Usuario generado: {}", user);
            
            // Use the old format for avatars (e.g., /images/avatar01.png)
            String avatarUrl = String.format("/images/avatar%02d.png", i);
            Profile profile = createProfile(user, faker.name().username(), avatarUrl);
            profile = profileRepository.save(profile);
            logger.info("Perfil generado: {}", profile);
            
            // Assign random songs to the profile
            for (SongEssence songEssence : songEssences) {
                Song song = createSong(profile, songEssence);
                songRepository.save(song);
                logger.info("Canción asignada al perfil {}: {}", profile.getId(), song);
            }
        }
    }
    
    private User createUser(String firstname, String lastname, String email, String username, LocalDate birthday) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setUsername(username);
        user.setBirthday(birthday);
        user.setPassword("securepassword"); // Default secure password
        return user;
    }
    
    private Profile createProfile(User user, String userName, String avatarUrl) {
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setUserName(userName);
        profile.setAvatarUrl(avatarUrl); // Manage avatars with the old format
        return profile;
    }
    
    private SongEssence createSongEssence(String name, String trackId, double danceability, double energy, double valence, double tempo) {
        SongEssence songEssence = new SongEssence();
        songEssence.setSongName(name);
        songEssence.setTrackId(trackId);
        songEssence.setDanceability(danceability);
        songEssence.setEnergy(energy);
        songEssence.setValence(valence);
        songEssence.setTempo(tempo);
        return songEssence;
    }
    
    private Song createSong(Profile profile, SongEssence songEssence) {
        Song song = new Song();
        song.setProfile(profile);
        song.setSongEssence(songEssence);
        song.setAddedDate(LocalDateTime.now());
        return song;
    }
}
