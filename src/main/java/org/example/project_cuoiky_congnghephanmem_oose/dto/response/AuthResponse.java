package org.example.project_cuoiky_congnghephanmem_oose.dto.response;

import lombok.*;

// dto/response/AuthResponse.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private String token;
    private String username;
    private String role;
}