package org.example.project_cuoiky_congnghephanmem_oose.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý lỗi validate @Valid (LoginRequest, RegisterRequest, RoomSearchRequest)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> {
                    Map<String, String> e = new HashMap<>();
                    e.put("field", err.getField());
                    e.put("defaultMessage", err.getDefaultMessage());
                    return e;
                })
                .collect(Collectors.toList());

        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("errors", errors);
        return ResponseEntity.badRequest().body(body);
    }

    // Xử lý AppException (lỗi nghiệp vụ tự định nghĩa)
    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handleAppException(AppException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", ex.getErrorCode().getHttpStatus());
        body.put("message", ex.getMessage());
        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(body);
    }

    // Bắt tất cả lỗi còn lại
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 500);
        body.put("message", "Lỗi máy chủ: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}