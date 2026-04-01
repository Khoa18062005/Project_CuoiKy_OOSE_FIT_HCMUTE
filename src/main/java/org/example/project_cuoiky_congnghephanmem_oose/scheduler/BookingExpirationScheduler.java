package org.example.project_cuoiky_congnghephanmem_oose.scheduler;

import org.example.project_cuoiky_congnghephanmem_oose.entity.Booking;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Payment;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IBookingRepository;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IPaymentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BookingExpirationScheduler {

    private final IBookingRepository bookingRepository;
    private final IPaymentRepository paymentRepository;

    public BookingExpirationScheduler(IBookingRepository bookingRepository,
                                      IPaymentRepository paymentRepository) {
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void cancelExpiredPendingBookings() {
        LocalDateTime now = LocalDateTime.now();

        List<Booking> expiredBookings = bookingRepository
                .findByStatusIgnoreCaseAndExpiredAtBefore("pending", now);

        if (expiredBookings.isEmpty()) {
            return;
        }

        for (Booking booking : expiredBookings) {
            booking.setStatus("cancelled");

            List<Payment> payments = paymentRepository
                    .findByBookingBookingIDOrderByPaymentIDDesc(booking.getBookingID());

            for (Payment payment : payments) {
                if ("pending".equalsIgnoreCase(payment.getStatus())) {
                    payment.setStatus("failed");
                }
            }

            paymentRepository.saveAll(payments);
        }

        bookingRepository.saveAll(expiredBookings);

        System.out.println("[BOOKING_EXPIRE_JOB] Da huy " + expiredBookings.size() + " booking het han luc " + now);
    }
}