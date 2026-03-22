package org.example.project_cuoiky_congnghephanmem_oose.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private LocalDate dateOfBirth;
    private String avatar;
    private int roleID;

    public boolean login() {
        return false;
    }

    public void logout() {
    }

    public void updateProfile() {
    }
}