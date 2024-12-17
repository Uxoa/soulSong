package io.soulsong.soulsong.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String first_name;
    private String last_name;
    private LocalDate birthday;
    private String email;
    private String phone_number;
    
    public User(String first_name, String last_name, LocalDate birthday, String email, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.email = email;
        this.phone_number = phone_number;
    }
}
