# Exception Layer

Xử lý lỗi tập trung cho toàn bộ ứng dụng.

## Quy tắc
- Dùng @RestControllerAdvice để bắt lỗi toàn cục
- Tạo custom exception riêng cho từng trường hợp
- Trả về response lỗi thống nhất

## Danh sách file cần tạo
- GlobalExceptionHandler.java  → bắt tất cả lỗi
- ResourceNotFoundException.java → không tìm thấy dữ liệu
- RoomNotAvailableException.java → phòng đã được đặt

## Ví dụ
@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
return ResponseEntity.status(404).body(ex.getMessage());
}
}