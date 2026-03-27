package org.example.project_cuoiky_congnghephanmem_oose.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_DATE_RANGE(400,   "Ngày trả phòng phải sau ngày nhận phòng"),
    INVALID_CHECKIN_DATE(400, "Ngày nhận phòng không được trong quá khứ"),
    INVALID_OCCUPANCY(400,    "Số khách phải từ 1 đến 10"),
    INVALID_ROOM_COUNT(400,   "Số phòng phải từ 1 đến 10"),
    NOT_ENOUGH_ROOMS(200,     "Không đủ số phòng trống theo yêu cầu"),
    ROOM_TYPE_NOT_FOUND(404,  "Loại phòng không tồn tại"),
    INTERNAL_ERROR(500,       "Lỗi máy chủ");

    private final int httpStatus;
    private final String message;

    ErrorCode(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}