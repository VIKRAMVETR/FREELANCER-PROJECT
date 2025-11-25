package com.freelancenexus.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Proposal
 *
 * <p>JPA entity representing a freelancer's proposal/bid for a project.
 * Stores proposal details including cover letter, budget, delivery timeline,
 * and AI-computed ranking score for client comparison.</p>
 *
 * <p>Proposals progress through a lifecycle: PENDING → ACCEPTED/REJECTED.
 * Multiple proposals can exist for a single project from different freelancers.</p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "proposals")
public class Proposal {

    /**
     * Primary key identifier for the proposal.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Many-to-one relationship with the Project entity.
     * Each proposal is submitted for exactly one project.
     * Lazy fetch type for performance optimization.
     *
     * @see Project
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    /**
     * Unique identifier of the freelancer submitting the proposal.
     */
    @Column(name = "freelancer_id", nullable = false)
    private Long freelancerId;

    /**
     * Cover letter or motivation statement from the freelancer explaining
     * why they are a good fit for the project.
     */
    @Column(name = "cover_letter", columnDefinition = "TEXT")
    private String coverLetter;

    /**
     * Freelancer's proposed budget for completing the project.
     * Precision: 10 digits total, 2 after decimal.
     */
    @Column(name = "proposed_budget", precision = 10, scale = 2)
    private BigDecimal proposedBudget;

    /**
     * Estimated number of days the freelancer needs to complete the project.
     */
    @Column(name = "delivery_days")
    private Integer deliveryDays;

    /**
     * AI-computed ranking score on a scale of 0-100 indicating the proposal's
     * quality and fit for the project. Computed by the AI service for client comparison.
     * Precision: 5 digits total, 2 after decimal.
     */
    @Column(name = "ai_score", precision = 5, scale = 2)
    private BigDecimal aiScore;

    /**
     * Current status of the proposal (PENDING, ACCEPTED, REJECTED).
     * Default value is PENDING.
     *
     * @see ProposalStatus
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProposalStatus status = ProposalStatus.PENDING;

    /**
     * Timestamp when the proposal was submitted. Managed by Hibernate.
     */
    @CreationTimestamp
    @Column(name = "submitted_at", nullable = false, updatable = false)
    private LocalDateTime submittedAt;

    /**
     * Timestamp when the proposal was last updated. Managed by Hibernate.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public Proposal(Long id, Project project, Long freelancerId, String coverLetter, BigDecimal proposedBudget,
			Integer deliveryDays, BigDecimal aiScore, ProposalStatus status, LocalDateTime submittedAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.project = project;
		this.freelancerId = freelancerId;
		this.coverLetter = coverLetter;
		this.proposedBudget = proposedBudget;
		this.deliveryDays = deliveryDays;
		this.aiScore = aiScore;
		this.status = status;
		this.submittedAt = submittedAt;
		this.updatedAt = updatedAt;
	}

	public Proposal() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}