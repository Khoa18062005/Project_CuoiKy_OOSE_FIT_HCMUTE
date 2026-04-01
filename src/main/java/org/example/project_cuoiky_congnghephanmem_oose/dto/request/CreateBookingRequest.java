package org.example.project_cuoiky_congnghephanmem_oose.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateBookingRequest {

    @NotNull
    private LocalDate checkin;

    @NotNull
    private LocalDate checkout;

    @NotEmpty
    private List<Integer> roomIds;

    @Min(1)
    private int guests;

    @Min(1)
    private int numberOfRooms;
}