package org.example.project_cuoiky_congnghephanmem_oose.service.payment;

import org.example.project_cuoiky_congnghephanmem_oose.dto.response.PaymentUrlResponse;

import java.util.Map;

public interface IPaymentService {
    PaymentUrlResponse createVnPayUrl(String username, int bookingId, String ipAddress);
    Map<String, String> handleVnPayReturn(Map<String, String> params);
}