package org.example.project_cuoiky_congnghephanmem_oose.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String password;
}