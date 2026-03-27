package org.example.project_cuoiky_congnghephanmem_oose.controller;

import jakarta.validation.Valid;
import org.example.project_cuoiky_congnghephanmem_oose.dto.request.RoomSearchRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RoomSearchResponse;
import org.example.project_cuoiky_congnghephanmem_oose.service.room.IRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final IRoomService roomService;

    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
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
}