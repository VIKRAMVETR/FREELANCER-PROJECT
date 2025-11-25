package com.freelancenexus.userservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.freelancenexus.userservice.model.ProjectStatus;

/**
 * ProjectDTO
 *
 * <p>Data Transfer Object representing project information returned by API endpoints.
 * Contains project details, status, budget information, required skills, and metadata.
 * Excludes sensitive information and is used for transferring project data to clients.</p>
 *
 * <p>Typically returned by project retrieval, creation, and update endpoints.</p>
 *
 * @since 1.0
 */
public class ProjectDTO {
    
    /**
     * Unique identifier of the project.
     */
    private Long id;
    
    /**
     * Unique identifier of the client who created the project.
     */
    private Long clientId;
    
    /**
     * Project title.
     */
    private String title;
    
    /**
     * Detailed description of the project and its requirements.
     */
    private String description;
    
    /**
     * Minimum budget amount for the project.
     */
    private BigDecimal budgetMin;
    
    /**
     * Maximum budget amount for the project.
     */
    private BigDecimal budgetMax;
    
    /**
     * Estimated project duration in days.
     */
    private Integer durationDays;
    
    /**
     * List of required skills to complete the project.
     */
    private List<String> requiredSkills;
    
    /**
     * Project category (e.g., "Web Development", "Mobile App", "Design").
     */
    private String category;
    
    /**
     * Current status of the project (e.g., OPEN, IN_PROGRESS, COMPLETED).
     *
     * @see ProjectStatus
     */
    private ProjectStatus status;
    
    /**
     * Project deadline date.
     */
    private LocalDate deadline;
    
    /**
     * Unique identifier of the freelancer assigned to the project (if any).
     * {@code null} if no freelancer is assigned.
     */
    private Long assignedFreelancer;
    
    /**
     * Timestamp when the project was created.
     */
    private LocalDateTime createdAt;
    
    /**
     * Timestamp when the project was last updated.
     */
    private LocalDateTime updatedAt;
    
    /**
     * Number of proposals submitted for this project.
     */
    private Integer proposalCount;

    private String clientName;
    
    private LocalDateTime clientJoinDate;
    
    private Long clientProjectCount;
   
    
	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public LocalDateTime getClientJoinDate() {
		return clientJoinDate;
	}


	public void setClientJoinDate(LocalDateTime clientJoinDate) {
		this.clientJoinDate = clientJoinDate;
	}


	public Long getClientProjectCount() {
		return clientProjectCount;
	}


	public void setClientProjectCount(Long clientProjectCount) {
		this.clientProjectCount = clientProjectCount;
	}


	public ProjectDTO(Long id, Long clientId, String title, String description, BigDecimal budgetMin,
			BigDecimal budgetMax, Integer durationDays, List<String> requiredSkills, String category,
			ProjectStatus status, LocalDate deadline, Long assignedFreelancer,
			LocalDateTime createdAt, LocalDateTime updatedAt, Integer proposalCount) {
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
		this.proposalCount = proposalCount;
	}

	
	public ProjectDTO(Long id, Long clientId, String title, String description, BigDecimal budgetMin,
			BigDecimal budgetMax, Integer durationDays, List<String> requiredSkills, String category,
			ProjectStatus status, LocalDate deadline, Long assignedFreelancer, LocalDateTime createdAt,
			LocalDateTime updatedAt, Integer proposalCount, String clientName, LocalDateTime clientJoinDate,
			Long clientProjectCount) {
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
		this.proposalCount = proposalCount;
		this.clientName = clientName;
		this.clientJoinDate = clientJoinDate;
		this.clientProjectCount = clientProjectCount;
	}


	public ProjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public List<String> getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(List<String> requiredSkills) {
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

	public Integer getProposalCount() {
		return proposalCount;
	}

	public void setProposalCount(Integer proposalCount) {
		this.proposalCount = proposalCount;
	}
	
	
    
    
}