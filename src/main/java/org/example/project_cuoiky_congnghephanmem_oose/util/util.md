# Util Layer

Các hàm tiện ích dùng chung trong toàn bộ ứng dụng.

## Danh sách file cần tạo
- VNPayUtil.java   → tạo chữ ký, hash thanh toán
- MailUtil.java    → format nội dung mail xác nhận
- DateUtil.java    → xử lý tính toán ngày tháng

## Ví dụ
public class DateUtil {
public static long countNights(LocalDate checkin, LocalDate checkout) {
return ChronoUnit.DAYS.between(checkin, checkout);
}
}