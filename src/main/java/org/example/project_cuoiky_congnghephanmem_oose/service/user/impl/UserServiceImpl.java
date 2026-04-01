package org.example.project_cuoiky_congnghephanmem_oose.service.user.impl;

import org.example.project_cuoiky_congnghephanmem_oose.dto.request.UpdateProfileRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.BookingHistoryResponse;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.BookingRoomItemResponse;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.UserProfileResponse;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Booking;
import org.example.project_cuoiky_congnghephanmem_oose.entity.BookingDetails;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Customer;
import org.example.project_cuoiky_congnghephanmem_oose.entity.MembershipTier;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Payment;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IBookingRepository;
import org.example.project_cuoiky_congnghephanmem_oose.repository.ICustomerRepository;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IMembershipTierRepository;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IPaymentRepository;
import org.example.project_cuoiky_congnghephanmem_oose.service.user.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final ICustomerRepository customerRepository;
    private final IBookingRepository bookingRepository;
    private final IMembershipTierRepository membershipTierRepository;
    private final IPaymentRepository paymentRepository;

    public UserServiceImpl(ICustomerRepository customerRepository,
                           IBookingRepository bookingRepository,
                           IMembershipTierRepository membershipTierRepository,
                           IPaymentRepository paymentRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.membershipTierRepository = membershipTierRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public UserProfileResponse getMyProfile(String username) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

        refreshTier(customer);

        MembershipTier tier = customer.getMembershipTier();
        return UserProfileResponse.builder()
                .userID(customer.getUserID())
                .username(customer.getUsername())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .dateOfBirth(customer.getDateOfBirth())
                .avatar(customer.getAvatar())
                .point(customer.getPoint())
                .membershipTier(tier != null ? tier.getTierName() : "Bronze")
                .discountRate(tier != null ? tier.getDiscountRate() : 0.0)
                .benefits(tier != null ? tier.getBenefits() : "Giá gốc, hỗ trợ cơ bản")
                .build();
    }

    @Override
    @Transactional
    public UserProfileResponse updateMyProfile(String username, UpdateProfileRequest request) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            customer.setUsername(request.getUsername().trim());
        }
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            customer.setEmail(request.getEmail().trim());
        }
        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            customer.setPhone(request.getPhone().trim());
        }
        if (request.getDateOfBirth() != null) {
            customer.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getAvatar() != null && !request.getAvatar().isBlank()) {
            customer.setAvatar(request.getAvatar().trim());
        }

        customerRepository.save(customer);
        return getMyProfile(customer.getUsername());
    }

    @Override
    @Transactional
    public List<BookingHistoryResponse> getMyBookingHistory(String username) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

        List<Booking> bookings = bookingRepository.findByCustomerUserIDOrderByBookingDateDesc(customer.getUserID());

        for (Booking booking : bookings) {
            syncExpiredBooking(booking);
        }

        return bookings.stream()
                .map(this::toBookingHistory)
                .toList();
    }

    private BookingHistoryResponse toBookingHistory(Booking booking) {
        List<BookingRoomItemResponse> rooms = booking.getBookingDetails()
                .stream()
                .map(this::toBookingRoomItem)
                .toList();

        BookingDetails first = booking.getBookingDetails()
                .stream()
                .min(Comparator.comparing(BookingDetails::getCheckinDate))
                .orElse(null);

        Payment latestPayment = paymentRepository.findTopByBookingBookingIDOrderByPaymentIDDesc(booking.getBookingID())
                .orElse(null);

        boolean expired = booking.getExpiredAt() != null && !booking.getExpiredAt().isAfter(LocalDateTime.now());
        boolean canRepay = "pending".equalsIgnoreCase(booking.getStatus()) && !expired;

        return BookingHistoryResponse.builder()
                .bookingID(booking.getBookingID())
                .bookingDate(booking.getBookingDate())
                .status(booking.getStatus())
                .paymentStatus(latestPayment != null ? latestPayment.getStatus() : "pending")
                .totalPrice(booking.getTotalPrice())
                .expiredAt(booking.getExpiredAt())
                .canRepay(canRepay)
                .expired(expired)
                .rooms(rooms)
                .checkin(first != null ? first.getCheckinDate() : null)
                .checkout(first != null ? first.getCheckoutDate() : null)
                .build();
    }

    private BookingRoomItemResponse toBookingRoomItem(BookingDetails detail) {
        return BookingRoomItemResponse.builder()
                .roomID(detail.getRoom().getRoomID())
                .roomNumber(detail.getRoom().getRoomNumber())
                .roomType(detail.getRoom().getRoomType().getTypeName())
                .pricePerNight(detail.getRoom().getRoomType().getPriceRoom())
                .subTotal(detail.getSubTotal())
                .build();
    }

    private void refreshTier(Customer customer) {
        membershipTierRepository.findTopByMinPointLessThanEqualOrderByMinPointDesc(customer.getPoint())
                .ifPresent(customer::setMembershipTier);
        customerRepository.save(customer);
    }

    private void syncExpiredBooking(Booking booking) {
        if (booking == null) return;

        if ("pending".equalsIgnoreCase(booking.getStatus())
                && booking.getExpiredAt() != null
                && !booking.getExpiredAt().isAfter(LocalDateTime.now())) {

            booking.setStatus("cancelled");
            bookingRepository.save(booking);

            paymentRepository.findByBookingBookingIDOrderByPaymentIDDesc(booking.getBookingID())
                    .forEach(payment -> {
                        if ("pending".equalsIgnoreCase(payment.getStatus())) {
                            payment.setStatus("failed");
                            paymentRepository.save(payment);
                        }
                    });
        }
    }
}