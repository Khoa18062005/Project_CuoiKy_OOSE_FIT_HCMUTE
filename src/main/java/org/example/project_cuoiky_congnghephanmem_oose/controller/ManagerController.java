// controller/ManagerController.java
package org.example.project_cuoiky_congnghephanmem_oose.controller;

import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RevenueResponse;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IRevenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private final IRevenueService revenueService;

    public ManagerController(IRevenueService revenueService) {
        this.revenueService = revenueService;
    }

    // GET /api/manager/revenue?period=week
    @GetMapping("/revenue")
    public ResponseEntity<RevenueResponse> getRevenue(
            @RequestParam(defaultValue = "week") String period) {
        return ResponseEntity.ok(revenueService.getRevenue(period));
    }
}