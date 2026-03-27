package org.example.project_cuoiky_congnghephanmem_oose.service.auth.impl;

import org.example.project_cuoiky_congnghephanmem_oose.dto.OtpData;
import org.example.project_cuoiky_congnghephanmem_oose.service.auth.IOtpService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpServiceImpl implements IOtpService {
    private final Map<String, OtpData> otpStorage = new ConcurrentHashMap<>();

    @Override
    public String generateOtp(String email) {
        String code = String.valueOf((int)((Math.random() * 899999) + 100000));
        otpStorage.put(email, new OtpData(code, LocalDateTime.now().plusMinutes(5)));
        return code;
    }

    @Override
    public boolean validateOtp(String email, String inputCode) {
        OtpData data = otpStorage.get(email);
        if (data == null || data.getExpiryTime().isBefore(LocalDateTime.now())) {
            return false;
        }
        return data.getCode().equals(inputCode);
    }
}