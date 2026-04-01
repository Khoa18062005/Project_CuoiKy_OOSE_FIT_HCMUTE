package org.example.project_cuoiky_congnghephanmem_oose.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRoomItemResponse {
    private int roomID;
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private double subTotal;
}