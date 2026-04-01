package org.example.project_cuoiky_congnghephanmem_oose.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateBookingResponse {
    private int bookingID;
    private String status;
    private double totalPrice;
    private LocalDateTime expiredAt;
    private String message;
    private double discountRate;        // ← THÊM
    private String discountInfo;        // ← THÊM (ví dụ: "Giảm 10%")
}