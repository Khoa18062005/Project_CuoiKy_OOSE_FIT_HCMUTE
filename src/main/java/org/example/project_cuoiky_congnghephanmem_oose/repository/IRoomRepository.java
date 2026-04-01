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

    @Query("""
        SELECT r FROM Rooms r
        WHERE LOWER(r.status) = 'available'
          AND (:roomType IS NULL OR LOWER(r.roomType.typeName) = LOWER(:roomType))
          AND r.roomType.occupancy >= :guestsPerRoom
          AND NOT EXISTS (
              SELECT bd FROM BookingDetails bd
              WHERE bd.room = r
                AND bd.checkinDate < :checkout
                AND bd.checkoutDate > :checkin
                AND (
                    LOWER(bd.booking.status) IN ('confirmed', 'checked_in')
                    OR (
                        LOWER(bd.booking.status) = 'pending'
                        AND bd.booking.expiredAt IS NOT NULL
                        AND bd.booking.expiredAt > CURRENT_TIMESTAMP
                    )
                )
          )
        ORDER BY r.roomType.priceRoom ASC
    """)
    List<Rooms> findAvailableRooms(
            @Param("checkin") LocalDate checkin,
            @Param("checkout") LocalDate checkout,
            @Param("roomType") String roomType,
            @Param("guestsPerRoom") int guestsPerRoom
    );

    @Query("""
        SELECT r FROM Rooms r
        WHERE r.roomID IN :roomIds
    """)
    List<Rooms> findAllByRoomIds(@Param("roomIds") List<Integer> roomIds);
}