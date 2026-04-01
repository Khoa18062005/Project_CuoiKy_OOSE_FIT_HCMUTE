// service/manager/impl/RoomManagementServiceImpl.java
package org.example.project_cuoiky_congnghephanmem_oose.service.manager.impl;

import org.example.project_cuoiky_congnghephanmem_oose.dto.request.UpdateRoomRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RoomDetailResponse;
import org.example.project_cuoiky_congnghephanmem_oose.entity.RoomTypes;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Rooms;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IBookingRepository;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IRoomRepository;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IRoomTypeRepository;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IRoomManagementService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomManagementServiceImpl implements IRoomManagementService {

    private final IRoomRepository roomRepository;
    private final IRoomTypeRepository roomTypeRepository;
    private final IBookingRepository bookingRepository;

    public RoomManagementServiceImpl(IRoomRepository roomRepository,
                                     IRoomTypeRepository roomTypeRepository,
                                     IBookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.bookingRepository = bookingRepository;
    }

    // GHI ĐÈ HÀM CŨ CỦA BẠN: Mặc định gọi logic ngày hôm nay
    @Override
    public List<RoomDetailResponse> getAllRooms() {
        return getRoomsByDate(null);
    }

    // GHI ĐÈ HÀM MỚI: Xử lý logic lọc phòng theo ngày được truyền vào
    @Override
    public List<RoomDetailResponse> getRoomsByDate(String dateParam) {
        LocalDate targetDate;
        if (dateParam == null || dateParam.isBlank()) {
            targetDate = LocalDate.now();
        } else {
            try {
                targetDate = LocalDate.parse(dateParam, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                targetDate = LocalDate.now();
            }
        }

        List<Integer> bookedRoomIdsForTargetDate = bookingRepository.findBookedRoomIdsByDate(targetDate);

        return roomRepository.findAll().stream()
                .map(room -> {
                    String currentStatus = room.getStatus();

                    if (!"maintenance".equalsIgnoreCase(currentStatus) && !"inactive".equalsIgnoreCase(currentStatus)) {
                        if (bookedRoomIdsForTargetDate.contains(room.getRoomID())) {
                            currentStatus = "booked";
                        } else {
                            currentStatus = "available";
                        }
                    }

                    return toResponseDynamic(room, currentStatus);
                })
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
            if (request.getStatus().equalsIgnoreCase("booked")) {
                throw new RuntimeException("Không được phép gán trạng thái 'Đã đặt' thủ công. Vui lòng tạo Booking.");
            }
            room.setStatus(request.getStatus());
        }

        roomRepository.save(room);
        return toResponseDynamic(room, room.getStatus());
    }

    private RoomDetailResponse toResponseDynamic(Rooms room, String dynamicStatus) {
        RoomTypes type = room.getRoomType();
        return new RoomDetailResponse(
                room.getRoomID(),
                room.getRoomNumber(),
                dynamicStatus,
                room.getDescription(),
                type != null ? type.getTypeID() : 0,
                type != null ? type.getTypeName() : "N/A",
                type != null ? type.getPriceRoom() : 0,
                type != null ? type.getOccupancy() : 0
        );
    }
}