package com.freelancenexus.userservice.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Data Transfer Object (DTO) for transferring ranked proposal data.
 * Contains details about the proposal, its ranking, and AI analysis.
 */


public class RankedProposalDTO {

    /**
     * The unique identifier of the proposal.
     */
    private Long id;

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
     * The rank of the proposal based on AI analysis.
     */
    private Integer rank;

    /**
     * The AI-generated analysis of the proposal.
     */
    private String aiAnalysis;

    /**
     * The list of strengths identified in the proposal.
     */
    private java.util.List<String> strengths;

    /**
     * The list of concerns identified in the proposal.
     */
    private java.util.List<String> concerns;

    /**
     * The timestamp when the proposal was submitted.
     */
    private LocalDateTime submittedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getAiAnalysis() {
		return aiAnalysis;
	}

	public void setAiAnalysis(String aiAnalysis) {
		this.aiAnalysis = aiAnalysis;
	}

	public java.util.List<String> getStrengths() {
		return strengths;
	}

	public void setStrengths(java.util.List<String> strengths) {
		this.strengths = strengths;
	}

	public java.util.List<String> getConcerns() {
		return concerns;
	}

	public void setConcerns(java.util.List<String> concerns) {
		this.concerns = concerns;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}

	public RankedProposalDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RankedProposalDTO(Long id, Long freelancerId, String coverLetter, BigDecimal proposedBudget,
			Integer deliveryDays, BigDecimal aiScore, Integer rank, String aiAnalysis, List<String> strengths,
			List<String> concerns, LocalDateTime submittedAt) {
		super();
		this.id = id;
		this.freelancerId = freelancerId;
		this.coverLetter = coverLetter;
		this.proposedBudget = proposedBudget;
		this.deliveryDays = deliveryDays;
		this.aiScore = aiScore;
		this.rank = rank;
		this.aiAnalysis = aiAnalysis;
		this.strengths = strengths;
		this.concerns = concerns;
		this.submittedAt = submittedAt;
	}
    
    
}