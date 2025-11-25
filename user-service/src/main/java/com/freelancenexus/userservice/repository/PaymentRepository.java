package com.freelancenexus.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancenexus.userservice.model.Payment;
import com.freelancenexus.userservice.model.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    Optional<Payment> findByTransactionId(String transactionId);
    
    List<Payment> findByProjectId(Long projectId);
    
    List<Payment> findByPayerId(Long payerId);
    
    List<Payment> findByPayeeId(Long payeeId);
    
    List<Payment> findByStatus(PaymentStatus status);
    
    List<Payment> findByPayerIdOrPayeeId(Long payerId, Long payeeId);
}