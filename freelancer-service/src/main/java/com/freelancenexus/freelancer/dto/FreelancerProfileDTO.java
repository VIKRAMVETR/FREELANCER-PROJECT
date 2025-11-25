package com.freelancenexus.freelancer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a freelancer's complete profile.
 * Includes details such as skills, portfolios, ratings, and earnings.
 */

public class FreelancerProfileDTO {
    
    /**
     * The unique identifier of the freelancer profile.
     */
    private Long id;

    /**
     * The user ID associated with the freelancer.
     */
    private Long userId;

    /**
     * The title or headline of the freelancer profile.
     */
    private String title;

    /**
     * The biography or description of the freelancer.
     */
    private String bio;

    /**
     * The hourly rate of the freelancer.
     */
    private BigDecimal hourlyRate;

    /**
     * The availability status of the freelancer.
     */
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
     * The list of portfolio items associated with the freelancer.
     */
    private List<PortfolioDTO> portfolios;

    /**
     * The list of recent ratings received by the freelancer.
     */
    private List<RatingDTO> recentRatings;
    
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

	public List<PortfolioDTO> getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(List<PortfolioDTO> portfolios) {
		this.portfolios = portfolios;
	}

	public List<RatingDTO> getRecentRatings() {
		return recentRatings;
	}

	public void setRecentRatings(List<RatingDTO> recentRatings) {
		this.recentRatings = recentRatings;
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

	public FreelancerProfileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FreelancerProfileDTO(Long id, Long userId, String title, String bio, BigDecimal hourlyRate,
			String availability, BigDecimal totalEarnings, Integer completedProjects, BigDecimal averageRating,
			List<SkillDTO> skills, List<PortfolioDTO> portfolios, List<RatingDTO> recentRatings,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.bio = bio;
		this.hourlyRate = hourlyRate;
		this.availability = availability;
		this.totalEarnings = totalEarnings;
		this.completedProjects = completedProjects;
		this.averageRating = averageRating;
		this.skills = skills;
		this.portfolios = portfolios;
		this.recentRatings = recentRatings;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
    
    
    
}