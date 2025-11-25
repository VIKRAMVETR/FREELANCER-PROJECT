package com.freelancenexus.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.freelancenexus.payment.model.PaymentStatus;

public class PaymentResponseDTO {
    
    private Long id;
    private String transactionId;
    private Long projectId;
    private Long payerId;
    private Long payeeId;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private String upiId;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
    private String description;
    private LocalDateTime createdAt;
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
	public PaymentResponseDTO(Long id, String transactionId, Long projectId, Long payerId, Long payeeId,
			BigDecimal amount, String currency, String paymentMethod, String upiId, PaymentStatus status,
			LocalDateTime paymentDate, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
	public PaymentResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}