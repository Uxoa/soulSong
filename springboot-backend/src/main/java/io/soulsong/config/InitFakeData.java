package io.soulsong.config;

import io.soulsong.entities.User;
import io.soulsong.entities.SongEssence;
import io.soulsong.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class InitFakeData {
    
    @Bean
    public CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            // Crear usuarios de prueba
            List<User> userList = List.of(
                  new User("Paloma", "García", LocalDate.of(1971, 4, 3), "paloma@example.com", "+34 600 123 456"),
                  new User("Antonio", "Martínez", LocalDate.of(1980, 2, 15), "antonio@example.com", "+34 600 987 654"),
                  new User("María", "Lopez", LocalDate.of(1990, 6, 10), "maria@example.com", "+34 622 111 333"),
                  new User("Sofía", "Ruiz", LocalDate.of(1995, 12, 20), "sofia@example.com", "+34 644 333 555")
            );
            
            // Crear SongEssence de prueba
            SongEssence song1 = new SongEssence(1L, "Happy", 0.8, 0.7, 120.0, 0.1);
            SongEssence song2 = new SongEssence(2L, "Melancholy", 0.6, 0.5, 100.0, 0.3);
            SongEssence song3 = new SongEssence(3L, "Energetic", 0.9, 0.8, 140.0, 0.05);
            
            // Agregar canciones favoritas a los perfiles
            userList.get(0).getProfile().addFavoriteSong(song1);
            userList.get(0).getProfile().addFavoriteSong(song2);
            
            userList.get(1).getProfile().addFavoriteSong(song3);
            
            // Guardar los usuarios en la base de datos
            userRepository.saveAll(userList);
            
            System.out.println("Usuarios de prueba guardados correctamente.");
        };
    }
}
