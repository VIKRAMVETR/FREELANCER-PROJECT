package com.freelancenexus.userservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.freelancenexus.userservice.model.ProposalStatus;
import com.freelancenexus.userservice.model.Skill;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * Data Transfer Object (DTO) for transferring proposal data.
 * Contains all the fields required for representing a proposal.
 */

public class ProposalDTO {

    /**
     * The unique identifier of the proposal.
     */
    private Long id;

    /**
     * The ID of the project associated with the proposal.
     */
    private Long projectId;

    /**
     * The title of the project associated with the proposal.
     */
    private String projectTitle;

    /**
     * The ID of the freelancer who submitted the proposal.
     */
    private Long freelancerId;

    /**
     * The cover letter submitted with the proposal.
     */
    private String coverLetter;

    /**
     * The budget proposed by the freelancer.
     */
    private BigDecimal proposedBudget;

    /**
     * The number of days proposed for delivery.
     */
    private Integer deliveryDays;

    /**
     * The AI-generated score for the proposal (0-100).
     */
    private BigDecimal aiScore;

    /**
     * The current status of the proposal.
     */
    @Enumerated(EnumType.STRING)
    private ProposalStatus status;

    /**
     * The timestamp when the proposal was submitted.
     */
    private LocalDateTime submittedAt;

    /**
     * The timestamp when the proposal was last updated.
     */
    private LocalDateTime updatedAt;

    private String projectDescription;
    
    
	private String freelancerName;
    private List<String> freelancerSkills;
    private BigDecimal freelancerRating;
    
    
    public String getFreelancerName() {
		return freelancerName;
	}

	public void setFreelancerName(String freelancerName) {
		this.freelancerName = freelancerName;
	}

	public List<String> getFreelancerSkills() {
		return freelancerSkills;
	}
	
	

	public void setFreelancerSkills(List<String> list) {
		this.freelancerSkills = list;
	}

	public BigDecimal getFreelancerRating() {
		return freelancerRating;
	}

	public void setFreelancerRating(BigDecimal freelancerRating) {
		this.freelancerRating = freelancerRating;
	}


	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

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

	public BigDecimal getAiScore() {
		return aiScore;
	}

	public void setAiScore(BigDecimal aiScore) {
		this.aiScore = aiScore;
	}

	public ProposalStatus getStatus() {
		return status;
	}

	public void setStatus(ProposalStatus status) {
		this.status = status;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public ProposalDTO(Long id, Long projectId, String projectTitle, Long freelancerId, String coverLetter,
			BigDecimal proposedBudget, Integer deliveryDays, BigDecimal aiScore, ProposalStatus status,
			LocalDateTime submittedAt, LocalDateTime updatedAt, String projectDescription) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.projectTitle = projectTitle;
		this.freelancerId = freelancerId;
		this.coverLetter = coverLetter;
		this.proposedBudget = proposedBudget;
		this.deliveryDays = deliveryDays;
		this.aiScore = aiScore;
		this.status = status;
		this.submittedAt = submittedAt;
		this.updatedAt = updatedAt;
		this.projectDescription = projectDescription;
	}

	public ProposalDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProposalDTO(Long id, Long projectId, String projectTitle, Long freelancerId, String coverLetter,
			BigDecimal proposedBudget, Integer deliveryDays, BigDecimal aiScore, ProposalStatus status,
			LocalDateTime submittedAt, LocalDateTime updatedAt, String projectDescription, String freelancerName,
			List<String> freelancerSkills, BigDecimal freelancerRating) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.projectTitle = projectTitle;
		this.freelancerId = freelancerId;
		this.coverLetter = coverLetter;
		this.proposedBudget = proposedBudget;
		this.deliveryDays = deliveryDays;
		this.aiScore = aiScore;
		this.status = status;
		this.submittedAt = submittedAt;
		this.updatedAt = updatedAt;
		this.projectDescription = projectDescription;
		this.freelancerName = freelancerName;
		this.freelancerSkills = freelancerSkills;
		this.freelancerRating = freelancerRating;
	}
    
    
    
}