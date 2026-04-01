package org.example.project_cuoiky_congnghephanmem_oose.controller;

import org.example.project_cuoiky_congnghephanmem_oose.dto.request.UpdateRoomRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.BookingListResponse;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.PotentialCustomerResponse;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RevenueResponse;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RoomDetailResponse;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IBookingListService;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IPotentialCustomerService;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IRevenueService;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IRoomManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private final IRevenueService revenueService;
    private final IPotentialCustomerService potentialCustomerService;
    private final IBookingListService bookingListService;
    private final IRoomManagementService roomManagementService;

    public ManagerController(IRevenueService revenueService,
                             IPotentialCustomerService potentialCustomerService,
                             IBookingListService bookingListService,
                             IRoomManagementService roomManagementService) {
        this.revenueService = revenueService;
        this.potentialCustomerService = potentialCustomerService;
        this.bookingListService = bookingListService;
        this.roomManagementService = roomManagementService;
    }

    // GET /api/manager/revenue?period=week
    @GetMapping("/revenue")
    public ResponseEntity<RevenueResponse> getRevenue(
            @RequestParam(defaultValue = "week") String period) {
        return ResponseEntity.ok(revenueService.getRevenue(period));
    }

    // GET /api/manager/potential-customers
    @GetMapping("/potential-customers")
    public ResponseEntity<List<PotentialCustomerResponse>> getPotentialCustomers() {
        return ResponseEntity.ok(potentialCustomerService.getPotentialCustomers());
    }

    // GET /api/manager/bookings?status=confirmed&keyword=nguyen
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingListResponse>> getBookings(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(bookingListService.getBookings(status, keyword));
    }

    // GET /api/manager/rooms
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDetailResponse>> getAllRooms() {
        return ResponseEntity.ok(roomManagementService.getAllRooms());
    }

    // PUT /api/manager/rooms/{roomID}
    @PutMapping("/rooms/{roomID}")
    public ResponseEntity<RoomDetailResponse> updateRoom(
            @PathVariable int roomID,
            @RequestBody UpdateRoomRequest request) {
        return ResponseEntity.ok(roomManagementService.updateRoom(roomID, request));
    }
}