# Response DTOs

Chứa dữ liệu server trả về cho client.
Không bao giờ trả Entity trực tiếp ra ngoài.

## Quy tắc
- Đặt tên: TênChứcNăng + Response (VD: BookingResponse)
- Chỉ chứa những field cần thiết, không expose password
- Dùng Lombok @Data, @Builder cho gọn

## Danh sách file cần tạo
- UserResponse.java           → thông tin user
- BookingResponse.java        → thông tin đơn đặt phòng
- BookingDetailsResponse.java → chi tiết từng phòng trong đơn
- PaymentResponse.java        → thông tin thanh toán
- RoomResponse.java           → thông tin phòng
- RoomTypeResponse.java       → thông tin loại phòng
- MembershipResponse.java     → thông tin hạng thành viên & điểm

## Ví dụ
@Data
@Builder
public class UserResponse {
private Integer userID;
private String userName;
private String email;
private String phone;
private String avatar;
private Integer point;
private String tierName;    // hạng thành viên hiện tại
// KHÔNG có password
}

@Data
@Builder
public class BookingResponse {
private Integer bookingID;
private LocalDate bookingDate;
private Double totalPrice;
private String status;          // pending, confirmed, cancelled
private LocalDate expiredAt;
private PaymentResponse payment;
private List<BookingDetailsResponse> details;
}

@Data
@Builder
public class RoomResponse {
private Integer roomID;
private String roomNumber;
private String status;
private String typeName;
private Double priceRoom;
private Integer occupancy;
private String description;
}