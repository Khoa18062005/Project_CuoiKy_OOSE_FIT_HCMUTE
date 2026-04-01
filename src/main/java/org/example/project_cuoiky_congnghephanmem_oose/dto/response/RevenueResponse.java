// dto/response/RevenueResponse.java
package org.example.project_cuoiky_congnghephanmem_oose.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class RevenueResponse {
    private double totalRevenue;
    private long totalBookings;
    private long bookedRoomsCount;
    private double avgDailyRevenue;
    private List<RevenueByDate> chartData;

    @Getter
    @AllArgsConstructor
    public static class RevenueByDate {
        private String date;
        private double revenue;
    }
}