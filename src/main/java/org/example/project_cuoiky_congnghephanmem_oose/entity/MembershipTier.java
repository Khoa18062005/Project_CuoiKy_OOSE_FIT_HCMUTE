package org.example.project_cuoiky_congnghephanmem_oose.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MembershipTier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tierID;
    private String tierName;
    private int minPoint;
    private double discountRate;
    private String benefits;

    public MembershipTier getTierByPoint(int point) {
        return null;
    }
}