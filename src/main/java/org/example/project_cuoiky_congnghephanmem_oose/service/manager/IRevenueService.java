package org.example.project_cuoiky_congnghephanmem_oose.service.manager;

import org.example.project_cuoiky_congnghephanmem_oose.dto.response.RevenueResponse;

public interface IRevenueService {
    RevenueResponse getRevenue(String period);
}