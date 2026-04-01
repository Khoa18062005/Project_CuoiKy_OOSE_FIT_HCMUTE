package org.example.project_cuoiky_congnghephanmem_oose.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomDetailResponse {
    private int roomID;
    private String roomNumber;
    private String status;
    private String description;
    private int typeID;
    private String typeName;
    private double priceRoom;
    private int occupancy;
}