package org.example.project_cuoiky_congnghephanmem_oose.service.manager.impl;

import org.example.project_cuoiky_congnghephanmem_oose.dto.request.UpdateRoomRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RoomDetailResponse;
import org.example.project_cuoiky_congnghephanmem_oose.entity.RoomTypes;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Rooms;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IRoomRepository;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IRoomTypeRepository;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IRoomManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomManagementServiceImpl implements IRoomManagementService {

    private final IRoomRepository roomRepository;
    private final IRoomTypeRepository roomTypeRepository;

    public RoomManagementServiceImpl(IRoomRepository roomRepository,
                                     IRoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public List<RoomDetailResponse> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDetailResponse updateRoom(int roomID, UpdateRoomRequest request) {
        Rooms room = roomRepository.findById(roomID)
                .orElseThrow(() -> new RuntimeException("Phòng không tồn tại!"));

        if (request.getTypeID() != 0) {
            RoomTypes type = roomTypeRepository.findById(request.getTypeID())
                    .orElseThrow(() -> new RuntimeException("Loại phòng không tồn tại!"));
            room.setRoomType(type);
        }

        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            room.setStatus(request.getStatus());
        }

        roomRepository.save(room);
        return toResponse(room);
    }

    private RoomDetailResponse toResponse(Rooms room) {
        RoomTypes type = room.getRoomType();
        return new RoomDetailResponse(
                room.getRoomID(),
                room.getRoomNumber(),
                room.getStatus(),
                room.getDescription(),
                type != null ? type.getTypeID() : 0,
                type != null ? type.getTypeName() : "N/A",
                type != null ? type.getPriceRoom() : 0,
                type != null ? type.getOccupancy() : 0
        );
    }
}