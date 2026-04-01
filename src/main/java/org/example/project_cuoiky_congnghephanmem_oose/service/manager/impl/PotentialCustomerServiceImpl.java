// service/manager/impl/PotentialCustomerServiceImpl.java
package org.example.project_cuoiky_congnghephanmem_oose.service.manager.impl;

import org.example.project_cuoiky_congnghephanmem_oose.dto.response.PotentialCustomerResponse;
import org.example.project_cuoiky_congnghephanmem_oose.entity.Customer;
import org.example.project_cuoiky_congnghephanmem_oose.repository.IBookingRepository;
import org.example.project_cuoiky_congnghephanmem_oose.service.manager.IPotentialCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PotentialCustomerServiceImpl implements IPotentialCustomerService {

    private final IBookingRepository bookingRepository;

    public PotentialCustomerServiceImpl(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<PotentialCustomerResponse> getPotentialCustomers() {
        List<Object[]> results = bookingRepository.findPotentialCustomers();

        return results.stream().map(row -> {
            Customer customer = (Customer) row[0];
            long totalBookings = (long) row[1];
            double totalSpent = (double) row[2];

            return new PotentialCustomerResponse(
                customer.getUserID(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getPhone(),
                totalBookings,
                totalSpent
            );
        }).collect(Collectors.toList());
    }
}