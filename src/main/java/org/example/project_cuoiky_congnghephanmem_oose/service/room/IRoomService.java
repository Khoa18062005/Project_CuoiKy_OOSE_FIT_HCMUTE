package org.example.project_cuoiky_congnghephanmem_oose.service.room;

import org.example.project_cuoiky_congnghephanmem_oose.dto.request.RoomSearchRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RoomSearchResponse;

public interface IRoomService {
    RoomSearchResponse searchAvailableRooms(RoomSearchRequest request);
}