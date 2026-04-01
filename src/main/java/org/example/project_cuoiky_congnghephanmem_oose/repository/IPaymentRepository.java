package org.example.project_cuoiky_congnghephanmem_oose.repository;

import org.example.project_cuoiky_congnghephanmem_oose.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByTransactionCode(String transactionCode);

    Optional<Payment> findTopByBookingBookingIDOrderByPaymentIDDesc(int bookingId);

    List<Payment> findByBookingBookingIDOrderByPaymentIDDesc(int bookingId);
}