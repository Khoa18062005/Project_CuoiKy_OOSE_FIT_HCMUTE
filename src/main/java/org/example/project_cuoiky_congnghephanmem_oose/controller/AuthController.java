package org.example.project_cuoiky_congnghephanmem_oose.controller;

import jakarta.validation.Valid;
import org.example.project_cuoiky_congnghephanmem_oose.dto.request.LoginRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.request.RegisterRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.AuthResponse;
import org.example.project_cuoiky_congnghephanmem_oose.service.auth.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}