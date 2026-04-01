// service/manager/impl/RevenueServiceImpl.java
package org.example.project_cuoiky_congnghephanmem_oose.service.manager.impl;

import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RevenueResponse;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Booking;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IBookingRepository;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IRevenueService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RevenueServiceImpl implements IRevenueService {

    private final IBookingRepository bookingRepository;

    public RevenueServiceImpl(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public RevenueResponse getRevenue(String period) {
        LocalDateTime from = getFromDate(period);
        LocalDateTime to = LocalDateTime.now();

        // Lấy booking confirmed trong khoảng thời gian
        List<Booking> bookings = (from == null)
                ? bookingRepository.findByStatus("confirmed")
                : bookingRepository.findByBookingDateBetweenAndStatus(from, to, "confirmed");

        // Tính tổng doanh thu
        double totalRevenue = bookings.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();

        // Số đơn booking
        long totalBookings = bookings.size();

        // Số phòng đã đặt (đếm bookingDetails)
        long bookedRoomsCount = bookings.stream()
                .mapToLong(b -> b.getBookingDetails() != null ? b.getBookingDetails().size() : 0)
                .sum();

        // Doanh thu trung bình / ngày
        long days = getDays(period);
        double avgDailyRevenue = days > 0 ? totalRevenue / days : totalRevenue;

        // Chart data — group theo ngày
        DateTimeFormatter formatter = getFormatter(period);
        Map<String, Double> grouped = new TreeMap<>();
        for (Booking b : bookings) {
            String dateKey = b.getBookingDate().format(formatter);
            grouped.merge(dateKey, b.getTotalPrice(), Double::sum);
        }

        List<RevenueResponse.RevenueByDate> chartData = grouped.entrySet().stream()
                .map(e -> new RevenueResponse.RevenueByDate(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        return new RevenueResponse(totalRevenue, totalBookings, bookedRoomsCount, avgDailyRevenue, chartData);
    }

    private LocalDateTime getFromDate(String period) {
        LocalDateTime now = LocalDateTime.now();
        return switch (period) {
            case "week"  -> now.minusDays(7);
            case "month" -> now.withDayOfMonth(1).toLocalDate().atStartOfDay();
            case "year"  -> now.withDayOfYear(1).toLocalDate().atStartOfDay();
            default      -> null; // "all"
        };
    }

    private long getDays(String period) {
        return switch (period) {
            case "week"  -> 7;
            case "month" -> LocalDate.now().lengthOfMonth();
            case "year"  -> LocalDate.now().lengthOfYear();
            default      -> 1;
        };
    }

    private DateTimeFormatter getFormatter(String period) {
        return switch (period) {
            case "year" -> DateTimeFormatter.ofPattern("MM/yyyy");
            default     -> DateTimeFormatter.ofPattern("dd/MM");
        };
    }
}