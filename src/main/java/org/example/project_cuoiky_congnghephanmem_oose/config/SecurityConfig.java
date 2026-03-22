package org.example.project_cuoiky_congnghephanmem_oose.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth

                // ===== PUBLIC — Không cần đăng nhập =====
                .requestMatchers(
                    "/api/auth/login",
                    "/api/auth/register",
                    "/api/rooms",
                    "/api/rooms/search",
                    "/api/room-types"
                ).permitAll()

                // ===== MANAGER ONLY =====
                .requestMatchers(
                    "/api/manager/**"
                ).hasRole("MANAGER")

                // ===== CUSTOMER — Phải đăng nhập =====
                .requestMatchers(
                    "/api/bookings/**",
                    "/api/payments/**",
                    "/api/users/**"
                ).hasRole("CUSTOMER")

                // Còn lại phải đăng nhập
                .anyRequest().authenticated()
            );

        return http.build();
    }
}