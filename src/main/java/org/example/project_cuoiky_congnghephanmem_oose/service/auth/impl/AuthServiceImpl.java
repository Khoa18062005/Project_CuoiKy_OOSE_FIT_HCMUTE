package org.example.project_cuoiky_congnghephanmem_oose.service.auth.impl;

import org.example.project_cuoiky_congnghephanmem_oose.dto.request.LoginRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.request.RegisterRequest;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.AuthResponse;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Customer;
import org.example.project_cuoiky_congnghephanmem_oose.entity.User;
import org.example.project_cuoiky_congnghephanmem_oose.repository.ICustomerRepository;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IUserRepository;
import org.example.project_cuoiky_congnghephanmem_oose.service.auth.IAuthService;
import org.example.project_cuoiky_congnghephanmem_oose.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository userRepository;
    private final ICustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; // ← thêm

    public AuthServiceImpl(IUserRepository userRepository,
                           ICustomerRepository customerRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) { // ← thêm
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu xác nhận không khớp!");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username đã tồn tại!");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng!");
        }

        // Tạo Customer thay vì User
        Customer customer = new Customer();
        customer.setUsername(request.getUsername());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setRoleID(1); // 1 = CUSTOMER

        customerRepository.save(customer); // ← cần ICustomerRepository

        return new AuthResponse("Đăng ký thành công!", null, customer.getUsername());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Username không tồn tại!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng!");
        }

        // Map roleID → tên role
        String role = user.getRoleID() == 2 ? "MANAGER" : "CUSTOMER";

        String token = jwtUtil.generateToken(user.getUsername(), role); // ← truyền role

        return new AuthResponse("Đăng nhập thành công!", token, user.getUsername());
    }
}