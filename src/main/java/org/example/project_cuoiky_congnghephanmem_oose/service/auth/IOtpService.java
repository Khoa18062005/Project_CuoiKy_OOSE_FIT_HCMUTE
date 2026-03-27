package org.example.project_cuoiky_congnghephanmem_oose.service.auth;

public interface IOtpService {
    String generateOtp(String email);
    boolean validateOtp(String email, String inputCode);
}