package com.freelancenexus.payment.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "transaction_history")
@Builder
public class TransactionHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;
    
    @Column(name = "status_change", length = 50, nullable = false)
    private String statusChange;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;
    
    @PrePersist
    protected void onCreate() {
        changedAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getStatusChange() {
		return statusChange;
	}

	public void setStatusChange(String statusChange) {
		this.statusChange = statusChange;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}

	public TransactionHistory(Long id, Long paymentId, String statusChange, String notes, LocalDateTime changedAt) {
		super();
		this.id = id;
		this.paymentId = paymentId;
		this.statusChange = statusChange;
		this.notes = notes;
		this.changedAt = changedAt;
	}

	public TransactionHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionHistory(Long paymentId, String statusChange, String notes) {
		this.paymentId = paymentId;
		this.statusChange = statusChange;
		this.notes = notes;
	}
    
    
}