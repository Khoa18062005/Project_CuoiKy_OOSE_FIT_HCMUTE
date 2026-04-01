// dto/response/PotentialCustomerResponse.java
package org.example.project_cuoiky_congnghephanmem_oose.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PotentialCustomerResponse {
    private int userID;
    private String username;
    private String email;
    private String phone;
    private long totalBookings;
    private double totalSpent;
}