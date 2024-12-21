package io.soulsong.dtos;

import io.soulsong.entities.User;

public class UserDTO {
    
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private ProfileDTO profile;
    
    public UserDTO() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public ProfileDTO getProfile() {
        return profile;
    }
    
    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }
    
    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        if (user.getProfile() != null) {
            dto.setProfile(ProfileDTO.fromEntityWithoutUser(user.getProfile())); // Evita referencia cíclica
        }
        return dto;
    }
    
    
    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setFirstname(this.firstname);
        user.setLastname(this.lastname);
        user.setEmail(this.email);
        user.setPassword(this.password);
        return user;
    }
    
   
}
