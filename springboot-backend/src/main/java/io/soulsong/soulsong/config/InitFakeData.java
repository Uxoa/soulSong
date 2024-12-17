package io.soulsong.soulsong.config;

import io.soulsong.soulsong.entities.User;
import io.soulsong.soulsong.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class InitFakeData {

    private UserRepository userRepository;
    
    public InitFakeData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
        @Bean
        public CommandLineRunner initData(UserRepository userRepository) {
            return args -> {
                // Crear una lista de usuarios de prueba
                List<User> userList = List.of(
                      new User("Paloma", "García", LocalDate.of(1971, 4, 3), "paloma@example.com", "+34 600 123 456"),
                      new User("Antonio", "Martínez", LocalDate.of(1980, 2, 15), "antonio@example.com", "+34 600 987 654"),
                      new User("María", "Lopez", LocalDate.of(1990, 6, 10), "maria@example.com", "+34 622 111 333"),
                      new User("Sofía", "Ruiz", LocalDate.of(1995, 12, 20), "sofia@example.com", "+34 644 333 555")
                );
                
                
                // Guardar los usuarios en la base de datos
                userRepository.saveAll(userList);
                
                System.out.println("Usuarios de prueba guardados correctamente.");
            };
        }
        
}

