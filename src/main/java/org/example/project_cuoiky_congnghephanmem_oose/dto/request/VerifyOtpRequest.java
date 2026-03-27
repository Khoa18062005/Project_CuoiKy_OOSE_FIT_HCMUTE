package org.example.project_cuoiky_congnghephanmem_oose.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerifyOtpRequest {
    private String email;
    private String otpCode;
}