package org.example.project_cuoiky_congnghephanmem_oose.repository;

import org.example.project_cuoiky_congnghephanmem_oose.entity.MembershipTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMembershipTierRepository extends JpaRepository<MembershipTier, Integer> {
    Optional<MembershipTier> findTopByMinPointLessThanEqualOrderByMinPointDesc(int point);
}