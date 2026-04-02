package org.example.project_cuoiky_congnghephanmem_oose.service.manager;

import org.example.project_cuoiky_congnghephanmem_oose.dto.request.UpdateRoomRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RoomDetailResponse;
import java.util.List;

public interface IRoomManagementService {

    // Giữ nguyên hoàn toàn dòng này theo ý bạn
    List<RoomDetailResponse> getAllRooms();

    // Thêm hàm này để phục vụ chức năng lọc theo ngày trên giao diện
    List<RoomDetailResponse> getRoomsByDate(String dateParam);

    RoomDetailResponse updateRoom(int roomID, UpdateRoomRequest request);
}