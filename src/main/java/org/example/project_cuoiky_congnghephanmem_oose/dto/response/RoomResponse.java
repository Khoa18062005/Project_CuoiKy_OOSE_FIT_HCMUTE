package org.example.project_cuoiky_congnghephanmem_oose.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {
    private int roomID;
    private String roomNumber;
    private String status;
    private String description;
    private RoomTypeResponse roomType;
}