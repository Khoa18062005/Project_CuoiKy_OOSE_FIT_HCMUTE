# Controller Layer

Nhận HTTP request từ client, gọi Service xử lý, trả về response.
Không chứa business logic.

## Quy tắc
- Mỗi entity có 1 controller riêng
- Chỉ được gọi Service, không gọi Repository trực tiếp
- Trả về ResponseEntity<?>

## Ví dụ
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
@GetMapping
public ResponseEntity<?> getAllRooms() {
return ResponseEntity.ok(roomService.getAllRooms());
}
