package io.soulsong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
              .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para pruebas
              .headers(headers -> headers
                    .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Permite iframes de la misma URL
              )
              .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/h2-console/**", "/api/**", "/*","/**").permitAll()
                    .anyRequest().authenticated()
              );
        
        return http.build();
    }
}

