// service/manager/impl/BookingListServiceImpl.java
package org.example.project_cuoiky_congnghephanmem_oose.service.manager.impl;

import org.example.project_cuoiky_congnghephanmem_oose.dto.response.BookingListResponse;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Booking;
import org.example.project_cuoiky_congnghephanmem_oose.entity.BookingDetails;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IBookingRepository;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IBookingListService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingListServiceImpl implements IBookingListService {

    private final IBookingRepository bookingRepository;

    public BookingListServiceImpl(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingListResponse> getBookings(String status, String keyword) {
        // "all" hoặc null → không filter status
        String statusParam = (status == null || status.equals("all")) ? null : status;
        // keyword rỗng → không filter keyword
        String keywordParam = (keyword == null || keyword.isBlank()) ? null : keyword;

        List<Booking> bookings = bookingRepository.findBookingsWithFilter(statusParam, keywordParam);

        return bookings.stream().map(b -> {
            List<BookingListResponse.BookingDetailItem> details = b.getBookingDetails()
                .stream()
                    .map(bd -> new BookingListResponse.BookingDetailItem(
                            bd.getRoom() != null ? bd.getRoom().getRoomNumber() : "N/A",
                            bd.getRoom() != null && bd.getRoom().getRoomType() != null
                                    ? bd.getRoom().getRoomType().getTypeName() : "N/A",
                            bd.getCheckinDate(),
                            bd.getCheckoutDate(),
                            (int) ChronoUnit.DAYS.between(bd.getCheckinDate(), bd.getCheckoutDate()),
                            bd.getSubTotal()
                    ))
                .collect(Collectors.toList());

            return new BookingListResponse(
                b.getBookingID(),
                b.getCustomer().getUsername(),
                b.getCustomer().getPhone(),
                b.getCustomer().getEmail(),
                b.getTotalPrice(),
                b.getStatus(),
                b.getBookingDate(),
                details
            );
        }).collect(Collectors.toList());
    }
}