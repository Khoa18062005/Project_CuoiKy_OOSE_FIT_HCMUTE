package org.example.project_cuoiky_congnghephanmem_oose.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username không được để trống")
    private String username;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại không hợp lệ")
    private String phone;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Mật khẩu kahông được để trống")
    @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
    private String password;

    @NotBlank(message = "Vui lòng xác nhận mật khẩu")
    private String confirmPassword;

    public @NotBlank(message = "Username không được để trống") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username không được để trống") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Số điện thoại không được để trống") @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại không hợp lệ") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "Số điện thoại không được để trống") @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại không hợp lệ") String phone) {
        this.phone = phone;
    }

    public @NotBlank(message = "Email không được để trống") @Email(message = "Email không đúng định dạng") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email không được để trống") @Email(message = "Email không đúng định dạng") String email) {
        this.email = email;
    }

    public @NotNull(message = "Ngày sinh không được để trống") @Past(message = "Ngày sinh phải là ngày trong quá khứ") LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotNull(message = "Ngày sinh không được để trống") @Past(message = "Ngày sinh phải là ngày trong quá khứ") LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotBlank(message = "Mật khẩu không được để trống") @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Mật khẩu không được để trống") @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Vui lòng xác nhận mật khẩu") String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotBlank(message = "Vui lòng xác nhận mật khẩu") String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}