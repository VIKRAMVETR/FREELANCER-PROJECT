package com.freelancenexus.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Project
 *
 * <p>JPA entity representing a freelance project posted by a client.
 * Stores project details, budget information, required skills, status,
 * and relationships to proposals and milestones.</p>
 *
 * <p>A project can have multiple proposals from different freelancers
 * and can be broken down into multiple milestones for progressive delivery.</p>
 *
 * @since 1.0
 */
@Entity
@Table(name = "projects")
public class Project {

    /**
     * Primary key identifier for the project.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique identifier of the client who created and owns the project.
     */
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    /**
     * Project title (required). Typically short and descriptive.
     */
    @Column(nullable = false)
    private String title;

    /**
     * Detailed project description including requirements, scope, and deliverables.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Minimum budget for the project. Precision: 10 digits total, 2 after decimal.
     */
    @Column(name = "budget_min", precision = 10, scale = 2)
    private BigDecimal budgetMin;

    /**
     * Maximum budget for the project. Precision: 10 digits total, 2 after decimal.
     */
    @Column(name = "budget_max", precision = 10, scale = 2)
    private BigDecimal budgetMax;

    /**
     * Estimated project duration in days.
     */
    @Column(name = "duration_days")
    private Integer durationDays;

    /**
     * Comma-separated or JSON array of required skills stored as a string.
     * Examples: "Java,Spring,AWS" or "[\"Java\", \"Spring\", \"AWS\"]"
     */
    @Column(name = "required_skills", columnDefinition = "TEXT")
    private String requiredSkills;

    /**
     * Project category (e.g., "Web Development", "Mobile App", "Design").
     * Max length: 100 characters.
     */
    @Column(length = 100)
    private String category;

    /**
     * Current status of the project (OPEN, IN_PROGRESS, COMPLETED, CANCELLED).
     * Default value is OPEN.
     *
     * @see ProjectStatus
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status = ProjectStatus.OPEN;

    /**
     * Project deadline date. Optional field.
     */
    private LocalDate deadline;

    /**
     * Unique identifier of the assigned freelancer (if any).
     * Null if no freelancer has been assigned yet.
     */
    @Column(name = "assigned_freelancer")
    private Long assignedFreelancer;

    /**
     * Timestamp when the project was created. Managed by Hibernate.
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the project was last updated. Managed by Hibernate.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * One-to-many relationship with Proposal entities.
     * A project can have multiple proposals from different freelancers.
     * Cascade delete ensures proposals are removed when the project is deleted.
     *
     * @see Proposal
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proposal> proposals = new ArrayList<>();

    /**
     * One-to-many relationship with ProjectMilestone entities.
     * A project can be broken down into multiple milestones for phased delivery.
     * Cascade delete ensures milestones are removed when the project is deleted.
     *
     * @see ProjectMilestone
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectMilestone> milestones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getBudgetMin() {
		return budgetMin;
	}

	public void setBudgetMin(BigDecimal budgetMin) {
		this.budgetMin = budgetMin;
	}

	public BigDecimal getBudgetMax() {
		return budgetMax;
	}

	public void setBudgetMax(BigDecimal budgetMax) {
		this.budgetMax = budgetMax;
	}

	public Integer getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(String requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public Long getAssignedFreelancer() {
		return assignedFreelancer;
	}

	public void setAssignedFreelancer(Long assignedFreelancer) {
		this.assignedFreelancer = assignedFreelancer;
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

	public List<Proposal> getProposals() {
		return proposals;
	}

	public void setProposals(List<Proposal> proposals) {
		this.proposals = proposals;
	}

	public List<ProjectMilestone> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<ProjectMilestone> milestones) {
		this.milestones = milestones;
	}

	public Project(Long id, Long clientId, String title, String description, BigDecimal budgetMin, BigDecimal budgetMax,
			Integer durationDays, String requiredSkills, String category, ProjectStatus status, LocalDate deadline,
			Long assignedFreelancer, LocalDateTime createdAt, LocalDateTime updatedAt, List<Proposal> proposals,
			List<ProjectMilestone> milestones) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.title = title;
		this.description = description;
		this.budgetMin = budgetMin;
		this.budgetMax = budgetMax;
		this.durationDays = durationDays;
		this.requiredSkills = requiredSkills;
		this.category = category;
		this.status = status;
		this.deadline = deadline;
		this.assignedFreelancer = assignedFreelancer;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.proposals = proposals;
		this.milestones = milestones;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}