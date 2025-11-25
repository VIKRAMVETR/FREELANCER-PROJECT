package com.freelancenexus.userservice.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancenexus.userservice.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    
    List<Portfolio> findByFreelancerId(Long freelancerId);
    
    Optional<Portfolio> findByIdAndFreelancerId(Long id, Long freelancerId);
    
    void deleteByFreelancerId(Long freelancerId);
    
    long countByFreelancerId(Long freelancerId);
}