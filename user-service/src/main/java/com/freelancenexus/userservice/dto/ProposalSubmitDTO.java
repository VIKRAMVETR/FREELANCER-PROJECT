package com.freelancenexus.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

/**
 * Data Transfer Object (DTO) for submitting a proposal.
 * Contains all the necessary fields and validation constraints for proposal submission.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalSubmitDTO {

    /**
     * The ID of the freelancer submitting the proposal.
     */
    @NotNull(message = "Freelancer ID is required")
    private Long freelancerId;

    /**
     * The cover letter submitted with the proposal.
     */
    @NotBlank(message = "Cover letter is required")
    @Size(min = 50, message = "Cover letter must be at least 50 characters")
    private String coverLetter;

    /**
     * The budget proposed by the freelancer.
     */
    @NotNull(message = "Proposed budget is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Proposed budget must be greater than 0")
    private BigDecimal proposedBudget;

    /**
     * The number of days proposed for delivery.
     */
    @NotNull(message = "Delivery days is required")
    @Min(value = 1, message = "Delivery days must be at least 1")
    private Integer deliveryDays;

	public Long getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(Long freelancerId) {
		this.freelancerId = freelancerId;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public BigDecimal getProposedBudget() {
		return proposedBudget;
	}

	public void setProposedBudget(BigDecimal proposedBudget) {
		this.proposedBudget = proposedBudget;
	}

	public Integer getDeliveryDays() {
		return deliveryDays;
	}

	public void setDeliveryDays(Integer deliveryDays) {
		this.deliveryDays = deliveryDays;
	}

	public ProposalSubmitDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProposalSubmitDTO(@NotNull(message = "Freelancer ID is required") Long freelancerId,
			@NotBlank(message = "Cover letter is required") @Size(min = 50, message = "Cover letter must be at least 50 characters") String coverLetter,
			@NotNull(message = "Proposed budget is required") @DecimalMin(value = "0.0", inclusive = false, message = "Proposed budget must be greater than 0") BigDecimal proposedBudget,
			@NotNull(message = "Delivery days is required") @Min(value = 1, message = "Delivery days must be at least 1") Integer deliveryDays) {
		super();
		this.freelancerId = freelancerId;
		this.coverLetter = coverLetter;
		this.proposedBudget = proposedBudget;
		this.deliveryDays = deliveryDays;
	}
    
    
}