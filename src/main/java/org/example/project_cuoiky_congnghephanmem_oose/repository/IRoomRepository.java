package org.example.project_cuoiky_congnghephanmem_oose.repository;

import org.example.project_cuoiky_congnghephanmem_oose.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRoomRepository extends JpaRepository<Rooms, Integer> {

    /**
     * Tìm tất cả phòng thỏa:
     *  1. status = 'available'
     *  2. Đúng loại phòng (hoặc tất cả nếu roomType = null)
     *  3. Sức chứa >= guestsPerRoom (= ceil(guests / numberOfRooms))
     *  4. Không bị đặt trùng khoảng ngày checkin–checkout
     *     (bỏ qua booking đã cancelled)
     *
     * numberOfRooms và limit() sẽ được xử lý ở Service.
     */
    @Query("""
        SELECT r FROM Rooms r
        WHERE r.status = 'available'
          AND (:roomType IS NULL OR LOWER(r.roomType.typeName) = LOWER(:roomType))
          AND r.roomType.occupancy >= :guestsPerRoom
          AND NOT EXISTS (
              SELECT bd FROM BookingDetails bd
              WHERE bd.room = r
                AND bd.checkinDate  < :checkout
                AND bd.checkoutDate > :checkin
                AND bd.booking.status NOT IN ('cancelled')
          )
        ORDER BY r.roomType.priceRoom ASC
    """)
    List<Rooms> findAvailableRooms(
            @Param("checkin")       LocalDate checkin,
            @Param("checkout")      LocalDate checkout,
            @Param("roomType")      String roomType,
            @Param("guestsPerRoom") int guestsPerRoom
    );

}