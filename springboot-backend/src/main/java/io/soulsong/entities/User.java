package io.soulsong.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "app_user") // Evitar conflicto con palabras reservadas como "user"
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generar ID automáticamente
    private Long id;
    
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String phoneNumber;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // Relación con Profile
    @JoinColumn(name = "profile_id", unique = true) // Garantizar unicidad
    private Profile profile = new Profile();
    
    public User() {
        this.profile = new Profile(); // Inicializar perfil automáticamente
        this.profile.setUser(this); // Vincular perfil al usuario
    }
    
    public User(String paloma, String garcía, LocalDate of, String mail, String s) {
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public LocalDate getBirthday() {
        return birthday;
    }
    
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Profile getProfile() {
        return profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
        if (profile != null) {
            profile.setUser(this); // Configurar la relación inversa
        }
    }
    
    
}
