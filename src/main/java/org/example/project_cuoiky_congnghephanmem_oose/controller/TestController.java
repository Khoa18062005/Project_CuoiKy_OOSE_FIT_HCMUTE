package org.example.project_cuoiky_congnghephanmem_oose.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String hello() {
        return "Hotel Booking API đang chạy!";
    }
}