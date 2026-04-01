package org.example.project_cuoiky_congnghephanmem_oose.service.auth;

import org.example.project_cuoiky_congnghephanmem_oose.entity.Booking;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Customer;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Payment;

public interface IEmailService {
    void sendOtpEmail(String toEmail, String otp);
    void sendBookingConfirmationEmail(Customer customer, Booking booking, Payment payment);
}