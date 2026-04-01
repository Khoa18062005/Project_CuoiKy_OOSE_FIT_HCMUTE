package org.example.project_cuoiky_congnghephanmem_oose.controller;

import jakarta.validation.Valid;
import org.example.project_cuoiky_congnghephanmem_oose.dto.request.CreateBookingRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.CreateBookingResponse;
import org.example.project_cuoiky_congnghephanmem_oose.service.booking.IBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final IBookingService bookingService;

    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<CreateBookingResponse> createBooking(
            Authentication authentication,
            @Valid @RequestBody CreateBookingRequest request
    ) {
        return ResponseEntity.ok(bookingService.createBooking(authentication.getName(), request));
    }
}