// service/manager/IPotentialCustomerService.java
package org.example.project_cuoiky_congnghephanmem_oose.service.manager;

import org.example.project_cuoiky_congnghephanmem_oose.dto.response.PotentialCustomerResponse;
import java.util.List;

public interface IPotentialCustomerService {
    List<PotentialCustomerResponse> getPotentialCustomers();
}