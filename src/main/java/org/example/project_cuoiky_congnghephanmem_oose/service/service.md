# Service Layer

Xử lý toàn bộ business logic của ứng dụng.

## Quy tắc
- Mỗi entity có 1 service riêng
- Chỉ được gọi Repository, không gọi Controller
- Dùng @Transactional khi có thao tác ghi DB

## Ví dụ
@Service
public class RoomService {
public List<RoomResponse> getAvailableRooms(LocalDate checkin, LocalDate checkout) {
// kiểm tra phòng trống theo ngày
}
}