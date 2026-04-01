// controller/ManagerController.java
package org.example.project_cuoiky_congnghephanmem_oose.controller;

import org.example.project_cuoiky_congnghephanmem_oose.dto.response.PotentialCustomerResponse;
import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RevenueResponse;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IPotentialCustomerService;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IRevenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private final IRevenueService revenueService;
    private final IPotentialCustomerService potentialCustomerService;

    public ManagerController(IRevenueService revenueService,
                             IPotentialCustomerService potentialCustomerService) {
        this.revenueService = revenueService;
        this.potentialCustomerService = potentialCustomerService;
    }

    // GET /api/manager/revenue?period=week
    @GetMapping("/revenue")
    public ResponseEntity<RevenueResponse> getRevenue(
            @RequestParam(defaultValue = "week") String period) {
        return ResponseEntity.ok(revenueService.getRevenue(period));
    }

    @GetMapping("/potential-customers")
    public ResponseEntity<List<PotentialCustomerResponse>> getPotentialCustomers() {
        return ResponseEntity.ok(potentialCustomerService.getPotentialCustomers());
    }
}