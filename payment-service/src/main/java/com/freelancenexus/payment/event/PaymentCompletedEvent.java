package com.freelancenexus.payment.event;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public class PaymentCompletedEvent implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long paymentId;
    private String transactionId;
    private Long projectId;
    private Long payerId;
    private Long payeeId;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime paymentDate;
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public PaymentCompletedEvent(Long paymentId, String transactionId, Long projectId, Long payerId, Long payeeId,
			BigDecimal amount, String currency, LocalDateTime paymentDate) {
		super();
		this.paymentId = paymentId;
		this.transactionId = transactionId;
		this.projectId = projectId;
		this.payerId = payerId;
		this.payeeId = payeeId;
		this.amount = amount;
		this.currency = currency;
		this.paymentDate = paymentDate;
	}
	public PaymentCompletedEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}