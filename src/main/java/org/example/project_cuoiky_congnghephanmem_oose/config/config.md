# Config Layer

Cấu hình các thành phần bên ngoài và bean của ứng dụng.

## Danh sách file cần tạo
- CorsConfig.java         → cho phép frontend gọi API
- SecurityConfig.java     → JWT, phân quyền Customer/Manager
- MailConfig.java         → cấu hình SMTP gửi mail
- VNPayConfig.java        → cấu hình thanh toán VNPay
- GeminiAIConfig.java     → cấu hình Google Gemini AI

## Ví dụ
@Configuration
public class CorsConfig {
@Bean
public CorsFilter corsFilter() {
// cho phép React/Vue gọi vào API
}
}