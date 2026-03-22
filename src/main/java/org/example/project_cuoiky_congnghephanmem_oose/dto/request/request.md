# Request DTOs

Chứa dữ liệu client gửi lên server.
Dùng để validate input trước khi xử lý.

## Quy tắc
- Đặt tên: TênChứcNăng + Request (VD: BookingRequest)
- Dùng @NotNull, @NotBlank để validate
- Không chứa logic xử lý

## Danh sách file cần tạo
- LoginRequest.java          → đăng nhập
- RegisterRequest.java       → đăng ký tài khoản
- BookingRequest.java        → tạo đơn đặt phòng
- PaymentRequest.java        → thanh toán
- UpdateProfileRequest.java  → cập nhật thông tin cá nhân
- SearchRoomRequest.java     → tìm kiếm phòng theo ngày/loại/số người

## Ví dụ
public class BookingRequest {
@NotNull(message = "roomID không được để trống")
private Integer roomID;

    @NotNull(message = "checkinDate không được để trống")
    private LocalDate checkinDate;

    @NotNull(message = "checkoutDate không được để trống")
    private LocalDate checkoutDate;
}

public class SearchRoomRequest {
@NotNull
private LocalDate checkinDate;

    @NotNull
    private LocalDate checkoutDate;

    private String typeName;    // basic / royal
    private Integer occupancy;  // số lượng người
}

public class LoginRequest {
@NotBlank(message = "Email không được để trống")
@Email(message = "Email không hợp lệ")
private String email;

    @NotBlank(message = "Password không được để trống")
    private String password;
}