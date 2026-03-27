package org.example.project_cuoiky_congnghephanmem_oose.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTypeResponse {
    private int typeID;
    private String typeName;
    private double priceRoom;
    private int occupancy;
}