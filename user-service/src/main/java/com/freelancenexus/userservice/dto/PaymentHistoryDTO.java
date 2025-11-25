package com.freelancenexus.userservice.dto;

import java.time.LocalDateTime;

public class PaymentHistoryDTO {
    
    private Long id;
    private Long paymentId;
    private String statusChange;
    private String notes;
    private LocalDateTime changedAt;
	public PaymentHistoryDTO(Long id, Long paymentId, String statusChange, String notes, LocalDateTime changedAt) {
		super();
		this.id = id;
		this.paymentId = paymentId;
		this.statusChange = statusChange;
		this.notes = notes;
		this.changedAt = changedAt;
	}
	public PaymentHistoryDTO() {
		super();
		// TODO Auto-generated constructor stub
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
    
    
    
}