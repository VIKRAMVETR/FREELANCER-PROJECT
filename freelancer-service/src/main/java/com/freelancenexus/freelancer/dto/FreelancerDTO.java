package com.freelancenexus.freelancer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for representing a freelancer.
 * Includes details such as title, bio, hourly rate, and skills.
 */
public class FreelancerDTO {
    
    /**
     * The unique identifier of the freelancer.
     */
    private Long id;
    
    /**
     * The user ID associated with the freelancer.
     * Must not be null.
     */
    @NotNull(message = "User ID is required")
    private Long userId;
    
    /**
     * The title or headline of the freelancer profile.
     * Must not be blank and cannot exceed 255 characters.
     */
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;
    
    /**
     * The biography or description of the freelancer.
     * Cannot exceed 5000 characters.
     */
    @Size(max = 5000, message = "Bio must not exceed 5000 characters")
    private String bio;
    
    /**
     * The hourly rate of the freelancer.
     * Must be positive and follow a valid format.
     */
    @DecimalMin(value = "0.0", inclusive = false, message = "Hourly rate must be positive")
    @Digits(integer = 8, fraction = 2, message = "Invalid hourly rate format")
    private BigDecimal hourlyRate;
    
    /**
     * The availability status of the freelancer.
     * Must match one of the predefined statuses.
     */
    @Pattern(regexp = "AVAILABLE|BUSY|UNAVAILABLE", message = "Invalid availability status")
    private String availability;
    
    /**
     * The total earnings of the freelancer.
     */
    private BigDecimal totalEarnings;

    /**
     * The number of completed projects by the freelancer.
     */
    private Integer completedProjects;

    /**
     * The average rating of the freelancer.
     */
    private BigDecimal averageRating;
    
    /**
     * The list of skills associated with the freelancer.
     */
    private List<SkillDTO> skills;
    
    /**
     * The timestamp when the freelancer profile was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when the freelancer profile was last updated.
     */
    private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public BigDecimal getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(BigDecimal hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public BigDecimal getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(BigDecimal totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	public Integer getCompletedProjects() {
		return completedProjects;
	}

	public void setCompletedProjects(Integer completedProjects) {
		this.completedProjects = completedProjects;
	}

	public BigDecimal getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(BigDecimal averageRating) {
		this.averageRating = averageRating;
	}

	public List<SkillDTO> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillDTO> skills) {
		this.skills = skills;
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

	public FreelancerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}