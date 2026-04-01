package org.example.project_cuoiky_congnghephanmem_oose.service.booking;

import org.example.project_cuoiky_congnghephanmem_oose.dto.request.CreateBookingRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.CreateBookingResponse;

public interface IBookingService {
    CreateBookingResponse createBooking(String username, CreateBookingRequest request);
}