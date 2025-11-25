package com.freelancenexus.payment.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PaymentRequestDTO {
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotNull(message = "Payer ID is required")
    private Long payerId;
    
    @NotNull(message = "Payee ID is required")
    private Long payeeId;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;
    
    @NotBlank(message = "Currency is required")
    @Size(max = 10)
    private String currency;
    
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
    
    @NotBlank(message = "UPI ID is required")
    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+$", message = "Invalid UPI ID format")
    private String upiId;
    
    private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PaymentRequestDTO(@NotNull(message = "Project ID is required") Long projectId,
			@NotNull(message = "Payer ID is required") Long payerId,
			@NotNull(message = "Payee ID is required") Long payeeId,
			@NotNull(message = "Amount is required") @DecimalMin(value = "0.01", message = "Amount must be greater than 0") BigDecimal amount,
			@NotBlank(message = "Currency is required") @Size(max = 10) String currency,
			@NotBlank(message = "Payment method is required") String paymentMethod,
			@NotBlank(message = "UPI ID is required") @Pattern(regexp = "^[\\w.-]+@[\\w.-]+$", message = "Invalid UPI ID format") String upiId,
			String description) {
		super();
		this.projectId = projectId;
		this.payerId = payerId;
		this.payeeId = payeeId;
		this.amount = amount;
		this.currency = currency;
		this.paymentMethod = paymentMethod;
		this.upiId = upiId;
		this.description = description;
	}

	public PaymentRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}