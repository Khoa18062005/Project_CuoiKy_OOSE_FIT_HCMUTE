package org.example.project_cuoiky_congnghephanmem_oose.service.manager;

import org.example.project_cuoiky_congnghephanmem_oose.dto.request.UpdateRoomRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RoomDetailResponse;

import java.util.List;

public interface IRoomManagementService {
    List<RoomDetailResponse> getAllRooms();
    RoomDetailResponse updateRoom(int roomID, UpdateRoomRequest request);
}