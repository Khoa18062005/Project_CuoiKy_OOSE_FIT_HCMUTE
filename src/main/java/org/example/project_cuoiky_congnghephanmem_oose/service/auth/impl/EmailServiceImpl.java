package org.example.project_cuoiky_congnghephanmem_oose.service.auth.impl;

import jakarta.mail.internet.MimeMessage;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Booking;
import org.example.project_cuoiky_congnghephanmem_oose.entity.BookingDetails;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Customer;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Payment;
import org.example.project_cuoiky_congnghephanmem_oose.service.auth.IEmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Mã OTP Reset Mật Khẩu");
        message.setText("Mã OTP của bạn là: " + otp + ". Hiệu lực trong 5 phút.");
        mailSender.send(message);
    }

    @Override
    public void sendBookingConfirmationEmail(Customer customer, Booking booking, Payment payment) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            StringBuilder roomRows = new StringBuilder();
            for (BookingDetails detail : booking.getBookingDetails()) {
                roomRows.append("""
                    <tr>
                        <td style="padding:10px;border:1px solid #eee;">%s</td>
                        <td style="padding:10px;border:1px solid #eee;">%s</td>
                        <td style="padding:10px;border:1px solid #eee;">%s → %s</td>
                        <td style="padding:10px;border:1px solid #eee;text-align:right;">%,.0f VNĐ</td>
                    </tr>
                """.formatted(
                        detail.getRoom().getRoomNumber(),
                        detail.getRoom().getRoomType().getTypeName(),
                        detail.getCheckinDate(),
                        detail.getCheckoutDate(),
                        detail.getSubTotal()
                ));
            }

            String html = """
                <div style="font-family:Arial,sans-serif;background:#f8fafc;padding:32px;">
                  <div style="max-width:720px;margin:auto;background:#ffffff;border-radius:20px;overflow:hidden;box-shadow:0 10px 30px rgba(0,0,0,0.08);">
                    <div style="background:linear-gradient(135deg,#ef4136,#fe7b18);padding:28px;color:#fff;">
                      <h1 style="margin:0;font-size:28px;">HOTEL STYLE</h1>
                      <p style="margin:8px 0 0;">Xác nhận thanh toán & đặt phòng thành công</p>
                    </div>
                    <div style="padding:28px;color:#334155;">
                      <p>Xin chào <b>%s</b>,</p>
                      <p>Cảm ơn bạn đã đặt phòng tại <b>Hotel Style</b>. Booking của bạn đã được thanh toán thành công.</p>

                      <div style="background:#fff7ed;border:1px solid #fed7aa;padding:18px;border-radius:14px;margin:20px 0;">
                        <p style="margin:0 0 8px;"><b>Mã booking:</b> #%s</p>
                        <p style="margin:0 0 8px;"><b>Trạng thái:</b> %s</p>
                        <p style="margin:0 0 8px;"><b>Mã giao dịch:</b> %s</p>
                        <p style="margin:0;"><b>Tổng tiền:</b> <span style="color:#ef4136;font-weight:700;">%,.0f VNĐ</span></p>
                      </div>

                      <table style="width:100%%;border-collapse:collapse;margin-top:16px;">
                        <thead>
                          <tr style="background:#f1f5f9;">
                            <th style="padding:10px;border:1px solid #eee;">Phòng</th>
                            <th style="padding:10px;border:1px solid #eee;">Loại</th>
                            <th style="padding:10px;border:1px solid #eee;">Thời gian</th>
                            <th style="padding:10px;border:1px solid #eee;">Thành tiền</th>
                          </tr>
                        </thead>
                        <tbody>
                          %s
                        </tbody>
                      </table>

                      <p style="margin-top:24px;">Chúc bạn có một kỳ nghỉ thật tuyệt vời ✨</p>
                    </div>
                  </div>
                </div>
            """.formatted(
                    customer.getUsername(),
                    booking.getBookingID(),
                    booking.getStatus(),
                    payment.getTransactionCode(),
                    booking.getTotalPrice(),
                    roomRows.toString()
            );

            helper.setTo(customer.getEmail());
            helper.setSubject("Xác nhận đặt phòng thành công - HOTEL STYLE");
            helper.setText(html, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("Gửi mail xác nhận thất bại: " + e.getMessage());
        }
    }
}