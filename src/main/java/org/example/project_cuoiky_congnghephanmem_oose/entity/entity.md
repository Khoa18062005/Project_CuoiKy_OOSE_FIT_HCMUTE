# Entity Layer

Mapping trực tiếp với các bảng trong database.

## Quy tắc
- Dùng @Entity, @Table(name = "tên_bảng")
- Primary key dùng @Id, @GeneratedValue
- Không chứa business logic
- Dùng Lombok (@Data, @Builder, @NoArgsConstructor)

## Danh sách Entity
- User.java          → bảng User
- Customer.java      → bảng Customer
- Manager.java       → bảng Manager
- Booking.java       → bảng Booking
- BookingDetails.java→ bảng BookingDetails
- Payment.java       → bảng Payment
- Rooms.java         → bảng Rooms
- RoomTypes.java     → bảng RoomTypes
- RoomTypeBeds.java  → bảng RoomTypeBeds
- Beds.java          → bảng Beds
- MembershipTier.java→ bảng MembershipTier

## Ví dụ
@Entity
@Table(name = "Rooms")
@Data
@NoArgsConstructor
public class Room {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer roomID;
private String roomNumber;
private String status;
}