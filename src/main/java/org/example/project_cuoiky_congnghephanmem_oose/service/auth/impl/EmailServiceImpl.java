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
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            String htmlContent = """
            <div style="margin:0;padding:0;background-color:#fffaf0;font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,sans-serif;">
                <table width="100%%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="center" style="padding:40px 20px;">
                            <table width="100%%" style="max-width:500px;background:#ffffff;border-radius:24px;overflow:hidden;box-shadow:0 15px 45px rgba(212,160,23,0.15);border:1px solid #f1e5d1;">
                                <tr>
                                    <td align="center" style="padding:45px 0 30px;background:linear-gradient(135deg, #FF8C00 0%%, #D4A017 100%%);">
                                        <div style="background:rgba(255,255,255,0.2);width:60px;height:60px;border-radius:50%%;margin-bottom:15px;line-height:60px;">
                                            <span style="color:#white;font-size:30px;">★</span>
                                        </div>
                                        <h1 style="margin:0;color:#ffffff;font-size:24px;letter-spacing:4px;text-transform:uppercase;font-weight:800;">HOTEL STYLE</h1>
                                        <p style="margin:5px 0 0;color:#ffffff;font-size:12px;letter-spacing:2px;opacity:0.9;">LUXURY EXPERIENCE</p>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td style="padding:40px;text-align:center;">
                                        <h2 style="color:#4a3f35;font-size:22px;font-weight:700;margin-bottom:15px;">Mã Xác Thực Của Bạn</h2>
                                        <p style="color:#7a6e66;font-size:15px;line-height:1.6;margin-bottom:35px;">Để đảm bảo an toàn cho tài khoản tại <b>Hotel Style</b>, vui lòng nhập mã OTP dưới đây để hoàn tất thủ tục.</p>
                                        
                                        <div style="background:#fff9f0;border-radius:16px;padding:30px;border:2px solid #ffd700;display:inline-block;min-width:240px;box-shadow:inset 0 0 10px rgba(255,140,0,0.05);">
                                            <span style="font-family:'Monaco','Consolas',monospace;font-size:42px;font-weight:bold;letter-spacing:10px;color:#d35400;text-shadow: 1px 1px 0px rgba(255,255,255,0.5);">%s</span>
                                        </div>
                                        
                                        <p style="color:#a8998d;font-size:13px;margin-top:35px;">Hiệu lực trong vòng <b style="color:#ff8c00;">5 phút</b>.<br>Nếu bạn không gửi yêu cầu này, vui lòng liên hệ bộ phận hỗ trợ.</p>
                                    </td>
                                </tr>

                                <tr>
                                    <td align="center" style="padding:30px;background:#fffdfa;border-top:1px solid #f1e5d1;">
                                        <p style="margin:0;font-style:italic;color:#d4a017;font-size:15px;font-weight:500;">"Chọn chúng tôi, chọn phong cách!"</p>
                                        <div style="margin-top:15px;border-top:1px solid #eee;padding-top:15px;width:50px;"></div>
                                        <p style="color:#c0b4ab;font-size:11px;margin-top:10px;text-transform:uppercase;letter-spacing:1px;">&copy; 2026 Hotel Style Management</p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
            """.formatted(otp);

            helper.setTo(toEmail);
            helper.setSubject("[HOTEL STYLE] Mã OTP Xác Thực Tài Khoản");
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("Gửi mail OTP thất bại: " + e.getMessage());
        }
    }

    @Override
    public void sendBookingConfirmationEmail(Customer customer, Booking booking, Payment payment) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // Xây dựng các dòng bảng phòng với tone màu nhẹ nhàng
            StringBuilder roomRows = new StringBuilder();
            for (BookingDetails detail : booking.getBookingDetails()) {
                roomRows.append("""
                <tr>
                    <td style="padding:15px; border-bottom:1px solid #f1e5d1; color:#4a3f35;"><b>%s</b></td>
                    <td style="padding:15px; border-bottom:1px solid #f1e5d1; color:#7a6e66;">%s</td>
                    <td style="padding:15px; border-bottom:1px solid #f1e5d1; color:#7a6e66;">%s <span style="color:#d4a017;">→</span> %s</td>
                    <td style="padding:15px; border-bottom:1px solid #f1e5d1; text-align:right; color:#d35400; font-weight:700;">%,.0f VNĐ</td>
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
            <div style="margin:0; padding:0; background-color:#fffaf0; font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,sans-serif;">
              <table width="100%%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td align="center" style="padding:32px 16px;">
                    <div style="max-width:720px; background:#ffffff; border-radius:24px; overflow:hidden; box-shadow:0 15px 45px rgba(212,160,23,0.1); border:1px solid #f1e5d1;">
                      
                      <div style="background:linear-gradient(135deg, #FF8C00 0%%, #D4A017 100%%); padding:40px; color:#ffffff; text-align:center;">
                        <h1 style="margin:0; font-size:28px; letter-spacing:4px; text-transform:uppercase; font-weight:800;">HOTEL STYLE</h1>
                        <p style="margin:10px 0 0; font-size:16px; opacity:0.9; letter-spacing:1px;">Xác nhận đặt phòng thành công</p>
                      </div>

                      <div style="padding:40px; color:#4a3f35;">
                        <p style="font-size:18px;">Xin chào <b style="color:#1d1d1f;">%s</b>,</p>
                        <p style="font-size:16px; line-height:1.6; color:#7a6e66;">Cảm ơn bạn đã tin tưởng lựa chọn <b>Hotel Style</b>. Chúng tôi rất hân hạnh được phục vụ bạn trong kỳ nghỉ sắp tới.</p>

                        <div style="background:#fff9f0; border:1px solid #ffd700; padding:25px; border-radius:18px; margin:30px 0; box-shadow:inset 0 0 10px rgba(212,160,23,0.05);">
                          <table width="100%%" border="0">
                            <tr>
                              <td style="padding:5px 0; color:#7a6e66;">Mã booking:</td>
                              <td style="padding:5px 0; text-align:right;"><b>#%s</b></td>
                            </tr>
                            <tr>
                              <td style="padding:5px 0; color:#7a6e66;">Trạng thái:</td>
                              <td style="padding:5px 0; text-align:right;"><span style="background:#d35400; color:#fff; padding:2px 10px; border-radius:10px; font-size:12px;">%s</span></td>
                            </tr>
                            <tr>
                              <td style="padding:5px 0; color:#7a6e66;">Mã giao dịch:</td>
                              <td style="padding:5px 0; text-align:right; font-family:monospace;">%s</td>
                            </tr>
                            <tr>
                              <td style="padding:15px 0 0 0; color:#4a3f35; font-size:18px; font-weight:700;">Tổng thanh toán:</td>
                              <td style="padding:15px 0 0 0; text-align:right; color:#d35400; font-size:22px; font-weight:800;">%,.0f VNĐ</td>
                            </tr>
                          </table>
                        </div>

                        <h3 style="font-size:18px; color:#4a3f35; margin-bottom:15px; border-left:4px solid #d4a017; padding-left:15px;">Chi tiết dịch vụ</h3>
                        <table style="width:100%%; border-collapse:collapse;">
                          <thead>
                            <tr style="background:#fff4e0;">
                              <th style="padding:12px; text-align:left; color:#d4a017; font-size:13px; text-transform:uppercase;">Phòng</th>
                              <th style="padding:12px; text-align:left; color:#d4a017; font-size:13px; text-transform:uppercase;">Loại</th>
                              <th style="padding:12px; text-align:left; color:#d4a017; font-size:13px; text-transform:uppercase;">Thời gian</th>
                              <th style="padding:12px; text-align:right; color:#d4a017; font-size:13px; text-transform:uppercase;">Thành tiền</th>
                            </tr>
                          </thead>
                          <tbody>
                            %s
                          </tbody>
                        </table>

                        <div style="margin-top:40px; text-align:center;">
                           <p style="color:#a8998d; font-size:14px; font-style:italic;">"Chọn chúng tôi, chọn phong cách!"</p>
                           <p style="margin-top:10px; font-size:20px;">✨ 🏨 ✨</p>
                        </div>
                      </div>

                      <div style="background:#fffdfa; padding:20px; text-align:center; border-top:1px solid #f1e5d1;">
                        <p style="color:#c0b4ab; font-size:11px; margin:0; text-transform:uppercase; letter-spacing:1px;">&copy; 2026 Hotel Style Management - 123 Luxury St, Saigon</p>
                      </div>
                    </div>
                  </td>
                </tr>
              </table>
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
            helper.setSubject("[HOTEL STYLE] Xác Nhận Đặt Phòng Thành Công - #" + booking.getBookingID());
            helper.setText(html, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("Gửi mail xác nhận thất bại: " + e.getMessage());
        }
    }
}