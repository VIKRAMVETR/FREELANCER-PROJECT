package com.freelancenexus.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancenexus.userservice.model.TransactionHistory;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    
    List<TransactionHistory> findByPaymentIdOrderByChangedAtDesc(Long paymentId);
    
    List<TransactionHistory> findByPaymentIdInOrderByChangedAtDesc(List<Long> paymentIds);
}