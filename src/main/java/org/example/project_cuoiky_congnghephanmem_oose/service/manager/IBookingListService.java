// service/manager/IBookingListService.java
package org.example.project_cuoiky_congnghephanmem_oose.service.manager;

import org.example.project_cuoiky_congnghephanmem_oose.dto.response.BookingListResponse;
import java.util.List;

public interface IBookingListService {
    List<BookingListResponse> getBookings(String status, String keyword);
}