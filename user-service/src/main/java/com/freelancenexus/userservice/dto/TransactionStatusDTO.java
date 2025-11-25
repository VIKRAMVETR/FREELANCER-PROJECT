package com.freelancenexus.userservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.freelancenexus.userservice.model.PaymentStatus;

public class TransactionStatusDTO {
    
    private String transactionId;
    private PaymentStatus status;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime timestamp;
    private String message;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
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
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TransactionStatusDTO(String transactionId, PaymentStatus status, BigDecimal amount, String currency,
			LocalDateTime timestamp, String message) {
		super();
		this.transactionId = transactionId;
		this.status = status;
		this.amount = amount;
		this.currency = currency;
		this.timestamp = timestamp;
		this.message = message;
	}
	public TransactionStatusDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}