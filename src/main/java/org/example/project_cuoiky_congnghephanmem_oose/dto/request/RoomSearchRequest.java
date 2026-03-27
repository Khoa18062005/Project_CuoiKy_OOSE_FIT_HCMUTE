package org.example.project_cuoiky_congnghephanmem_oose.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomSearchRequest {

    @NotNull(message = "Ngày nhận phòng không được để trống")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkin;

    @NotNull(message = "Ngày trả phòng không được để trống")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkout;

    // null hoặc "all" = tìm tất cả loại phòng
    private String roomType;

    @Min(value = 1, message = "Số khách tối thiểu là 1")
    @Max(value = 10, message = "Số khách tối đa là 10")
    private int guests = 1;

    @Min(value = 1, message = "Số phòng tối thiểu là 1")
    @Max(value = 10, message = "Số phòng tối đa là 10")
    private int numberOfRooms = 1;
}