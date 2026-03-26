# Repository Layer

Tương tác trực tiếp với database thông qua JPA.

## Quy tắc
- Extend JpaRepository<Entity, ID>
- Đặt tên method theo quy tắc Spring Data JPA
- Query phức tạp dùng @Query với JPQL hoặc Native SQL

## Ví dụ
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
// Tìm phòng trống theo ngày
@Query("SELECT r FROM Room r WHERE r.roomID NOT IN (" +
"SELECT bd.roomID FROM BookingDetails bd " +
"JOIN bd.booking b " +
"WHERE b.status IN ('confirmed', 'checked_in') " +
"AND bd.checkinDate < :checkout AND bd.checkoutDate > :checkin)")
List<Room> findAvailableRooms(LocalDate checkin, LocalDate checkout);
}