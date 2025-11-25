package com.freelancenexus.userservice.dto;

import java.math.BigDecimal;

public class UPIPaymentDTO {
    
    private String transactionId;
    private String upiId;
    private BigDecimal amount;
    private String currency;
    private String paymentLink;
    private String qrCode;
    private Long expiresIn; // seconds
    private String status;
    private String message;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getUpiId() {
		return upiId;
	}
	public void setUpiId(String upiId) {
		this.upiId = upiId;
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
	public String getPaymentLink() {
		return paymentLink;
	}
	public void setPaymentLink(String paymentLink) {
		this.paymentLink = paymentLink;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public Long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UPIPaymentDTO(String transactionId, String upiId, BigDecimal amount, String currency, String paymentLink,
			String qrCode, Long expiresIn, String status, String message) {
		super();
		this.transactionId = transactionId;
		this.upiId = upiId;
		this.amount = amount;
		this.currency = currency;
		this.paymentLink = paymentLink;
		this.qrCode = qrCode;
		this.expiresIn = expiresIn;
		this.status = status;
		this.message = message;
	}
	public UPIPaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}