// dto/response/BookingListResponse.java
package org.example.project_cuoiky_congnghephanmem_oose.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookingListResponse {
    private int bookingID;
    private String username;
    private String phone;
    private String email;
    private double totalPrice;
    private String status;
    private LocalDateTime bookingDate;
    private List<BookingDetailItem> details;

    @Getter
    @AllArgsConstructor
    public static class BookingDetailItem {
        private String roomNumber;  // ← đổi tên
        private String roomType;    // ← thêm
        private LocalDate checkinDate;
        private LocalDate checkoutDate;
        private int nights;
        private double subTotal;
    }
}