# DTO (Data Transfer Object)

Dữ liệu trao đổi giữa client và server.
Không expose Entity trực tiếp ra ngoài.

## Cấu trúc
- request/  → dữ liệu client gửi lên
- response/ → dữ liệu server trả về

## Ví dụ request
public class BookingRequest {
private Integer roomID;
private LocalDate checkinDate;
private LocalDate checkoutDate;
}

## Ví dụ response
public class BookingResponse {
private Integer bookingID;
private String status;
private Double totalPrice;
private LocalDate bookingDate;
}