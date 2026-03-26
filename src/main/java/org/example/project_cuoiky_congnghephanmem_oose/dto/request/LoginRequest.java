package org.example.project_cuoiky_congnghephanmem_oose.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    
    @NotBlank(message = "Username không được để trống")
    private String username;
    
    @NotBlank(message = "Password không được để trống")
    private String password;

    public @NotBlank(message = "Username không được để trống") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username không được để trống") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password không được để trống") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password không được để trống") String password) {
        this.password = password;
    }


}