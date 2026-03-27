package org.example.project_cuoiky_congnghephanmem_oose.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter @AllArgsConstructor
public class OtpData {
    private String code;
    private LocalDateTime expiryTime;
}