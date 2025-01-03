package io.soulsong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
              .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para pruebas
              .headers(headers -> headers
                    .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Permite iframes de la misma URL
              )
              .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/h2-console/**", "/profiles/**", "/api/**", "/*", "/**").permitAll()
                    .anyRequest().authenticated()
              );
        
        return http.build();
    }
}
