package org.example.project_cuoiky_congnghephanmem_oose.config;

import org.example.project_cuoiky_congnghephanmem_oose.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter; // ← thêm

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) { // ← thêm constructor
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)  // ← thêm dòng này
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // PUBLIC
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/register",
                                "/api/rooms",
                                "/api/rooms/search",
                                "/api/room-types",
                                "/error",
                                "/api/auth/forgot-password",
                                "/api/auth/verify-otp"
                        ).permitAll()

                        // MANAGER ONLY
                        .requestMatchers("/api/manager/**").hasRole("MANAGER")

                        // CUSTOMER
                        .requestMatchers(
                                "/api/bookings/**",
                                "/api/payments/**",
                                "/api/users/**"
                        ).hasRole("CUSTOMER")

                        .anyRequest().authenticated()
                ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}