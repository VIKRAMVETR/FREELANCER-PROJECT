package com.freelancenexus.payment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "payments")
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "transaction_id", unique = true, nullable = false)
    private String transactionId;
    
    @Column(name = "project_id", nullable = false)
    private Long projectId;
    
    @Column(name = "payer_id", nullable = false)
    private Long payerId;  // Client user ID
    
    @Column(name = "payee_id", nullable = false)
    private Long payeeId;  // Freelancer user ID
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    @Column(length = 10, nullable = false)
    private String currency;
    
    @Column(name = "payment_method", length = 50)
    private String paymentMethod;
    
    @Column(name = "upi_id", length = 100)
    private String upiId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;
    
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getPayerId() {
		return payerId;
	}

	public void setPayerId(Long payerId) {
		this.payerId = payerId;
	}

	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Payment(String transactionId, Long projectId, Long payerId, Long payeeId, BigDecimal amount,
			String currency, String paymentMethod, String upiId, PaymentStatus status, String description) {
		super();
		this.id = id;
		this.transactionId = transactionId;
		this.projectId = projectId;
		this.payerId = payerId;
		this.payeeId = payeeId;
		this.amount = amount;
		this.currency = currency;
		this.paymentMethod = paymentMethod;
		this.upiId = upiId;
		this.status = status;
		this.paymentDate = paymentDate;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Payment(Long id, String transactionId, Long projectId, Long payerId, Long payeeId, BigDecimal amount,
			String currency, String paymentMethod, String upiId, PaymentStatus status, LocalDateTime paymentDate,
			String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.transactionId = transactionId;
		this.projectId = projectId;
		this.payerId = payerId;
		this.payeeId = payeeId;
		this.amount = amount;
		this.currency = currency;
		this.paymentMethod = paymentMethod;
		this.upiId = upiId;
		this.status = status;
		this.paymentDate = paymentDate;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}