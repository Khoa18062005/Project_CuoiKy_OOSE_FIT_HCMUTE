package org.example.project_cuoiky_congnghephanmem_oose.repository;

import org.example.project_cuoiky_congnghephanmem_oose.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCustomerUserIDOrderByBookingDateDesc(int userId);

    List<Booking> findByStatusIgnoreCaseAndExpiredAtBefore(String status, LocalDateTime expiredAt);

    List<Booking> findByBookingDateBetweenAndStatus(
            LocalDateTime from, LocalDateTime to, String status
    );
    List<Booking> findByStatus(String status);

    // Lấy tất cả booking confirmed, group theo customer, sort theo tổng tiền giảm dần
    @Query("""
    SELECT b.customer, COUNT(b), SUM(b.totalPrice)
    FROM Booking b
    WHERE b.status = 'confirmed'
    GROUP BY b.customer
    ORDER BY SUM(b.totalPrice) DESC
""")
    List<Object[]> findPotentialCustomers();

    @Query("""
        SELECT DISTINCT b FROM Booking b
        LEFT JOIN FETCH b.bookingDetails bd
        LEFT JOIN FETCH bd.room
        LEFT JOIN FETCH b.customer c
        WHERE (:status IS NULL OR b.status = :status)
        AND (
            :keyword IS NULL
            OR LOWER(c.username) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.phone) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
        )
        ORDER BY b.bookingDate DESC
    """)
    List<Booking> findBookingsWithFilter(
            @Param("status") String status,
            @Param("keyword") String keyword
    );
}