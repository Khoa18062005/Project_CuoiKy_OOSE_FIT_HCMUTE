package org.example.project_cuoiky_congnghephanmem_oose.controller;

import jakarta.validation.Valid;
import org.example.project_cuoiky_congnghephanmem_oose.dto.request.RoomSearchRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RoomDetailResponse;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RoomSearchResponse;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IRoomManagementService;
import org.example.project_cuoiky_congnghephanmem_oose.service.room.IRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final IRoomService roomService;
    private final IRoomManagementService roomManagementService;

    public RoomController(IRoomService roomService, IRoomManagementService roomManagementService) {
        this.roomService = roomService;
        this.roomManagementService = roomManagementService;
    }

    /**
     * GET /api/rooms/search
     * ?checkin=2025-08-01&checkout=2025-08-03&guests=2&numberOfRooms=2&roomType=Basic
     * PUBLIC – không cần JWT
     */
    @GetMapping("/search")
    public ResponseEntity<RoomSearchResponse> searchRooms(@Valid RoomSearchRequest request) {
        return ResponseEntity.ok(roomService.searchAvailableRooms(request));
    }

    @GetMapping
    public ResponseEntity<List<RoomDetailResponse>> getRooms(@RequestParam(required = false) String date) {
        // Nếu Frontend truyền date lên thì dùng getRoomsByDate
        if (date != null && !date.isBlank()) {
            return ResponseEntity.ok(roomManagementService.getRoomsByDate(date));
        }
        // Nếu Frontend không truyền (hoặc mới load trang) thì dùng hàm mặc định của bạn
        return ResponseEntity.ok(roomManagementService.getAllRooms());
    }
}