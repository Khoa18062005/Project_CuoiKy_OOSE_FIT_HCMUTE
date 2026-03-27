package org.example.project_cuoiky_congnghephanmem_oose.dto.response;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomSearchResponse {
    private int totalFound;       // tổng số phòng tìm được
    private int requested;        // số phòng khách yêu cầu
    private boolean enough;       // có đủ phòng không
    private String warning;       // cảnh báo nếu không đủ (null nếu đủ)
    private List<RoomResponse> rooms; // danh sách phòng trả về
}