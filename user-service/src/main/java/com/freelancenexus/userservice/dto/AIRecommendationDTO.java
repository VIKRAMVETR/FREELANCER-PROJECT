package com.freelancenexus.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * AIRecommendationDTO
 *
 * <p>Data Transfer Object representing an AI-recommended project for a freelancer.
 * Contains project details, required skills, and AI-computed match scoring metrics
 * that indicate how well the project aligns with the freelancer's profile.</p>
 *
 * <p>Returned by AI recommendation endpoints to help freelancers discover suitable projects.</p>
 *
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIRecommendationDTO {
    
    /**
     * Unique identifier of the recommended project.
     */
    private Long projectId;
    
    /**
     * Title/name of the project.
     */
    private String projectTitle;
    
    /**
     * Project category (e.g., "Web Development", "Mobile App", "Design").
     */
    private String category;
    
    /**
     * Minimum budget amount for the project.
     */
    private BigDecimal budgetMin;
    
    /**
     * Maximum budget amount for the project.
     */
    private BigDecimal budgetMax;
    
    /**
     * Estimated duration of the project in days.
     */
    private Integer durationDays;
    
    /**
     * List of skills required to complete the project.
     */
    private List<String> requiredSkills;
    
    /**
     * AI-computed match score on a scale of 0-100 indicating overall project compatibility.
     * Higher scores indicate better alignment with the freelancer's profile.
     */
    private BigDecimal matchScore;
    
    /**
     * Human-readable explanation of why this project is recommended
     * (e.g., reasons for the match score).
     */
    private String matchReason;
    
    /**
     * List of freelancer skills that match the project's required skills.
     */
    private List<String> matchingSkills;
    
    /**
     * Percentage of project-required skills that the freelancer possesses (0-100).
     */
    private Integer skillMatchPercentage;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public BigDecimal getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(BigDecimal matchScore) {
		this.matchScore = matchScore;
	}

	public String getMatchReason() {
		return matchReason;
	}

	public void setMatchReason(String matchReason) {
		this.matchReason = matchReason;
	}

	public List<String> getMatchingSkills() {
		return matchingSkills;
	}

	public void setMatchingSkills(List<String> matchingSkills) {
		this.matchingSkills = matchingSkills;
	}

	public Integer getSkillMatchPercentage() {
		return skillMatchPercentage;
	}

	public void setSkillMatchPercentage(Integer skillMatchPercentage) {
		this.skillMatchPercentage = skillMatchPercentage;
	}

	public AIRecommendationDTO(Long projectId, String projectTitle, String category, BigDecimal budgetMin,
			BigDecimal budgetMax, Integer durationDays, List<String> requiredSkills, BigDecimal matchScore,
			String matchReason, List<String> matchingSkills, Integer skillMatchPercentage) {
		super();
		this.projectId = projectId;
		this.projectTitle = projectTitle;
		this.category = category;
		this.budgetMin = budgetMin;
		this.budgetMax = budgetMax;
		this.durationDays = durationDays;
		this.requiredSkills = requiredSkills;
		this.matchScore = matchScore;
		this.matchReason = matchReason;
		this.matchingSkills = matchingSkills;
		this.skillMatchPercentage = skillMatchPercentage;
	}

	public AIRecommendationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}