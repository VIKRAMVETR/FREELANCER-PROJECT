package com.freelancenexus.userservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProjectSummaryDTO
 *
 * <p>Data Transfer Object representing an AI-generated summary of a project.
 * Contains concise summaries, key requirements, ideal candidate profile, and
 * complexity assessment produced by the AI service.</p>
 *
 * <p>Returned by AI summary endpoints to provide clients and freelancers with
 * quick, AI-generated insights about a project.</p>
 *
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSummaryDTO {
    
    /**
     * Unique identifier of the project being summarized.
     */
    private Long projectId;
    
    /**
     * AI-generated concise summary of the project's objectives and scope.
     */
    private String summary;
    
    /**
     * AI-extracted key requirements and deliverables for the project.
     */
    private String keyRequirements;
    
    /**
     * AI-generated description of the ideal candidate profile
     * (experience level, skill set, background).
     */
    private String idealCandidate;
    
    /**
     * AI-assessed complexity level of the project
     * (e.g., "Low", "Medium", "High", "Very High").
     */
    private String estimatedComplexity;
    
    /**
     * List of suggested skills recommended by AI for the project,
     * based on analysis of the project description and requirements.
     */
    private java.util.List<String> suggestedSkills;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getKeyRequirements() {
		return keyRequirements;
	}

	public void setKeyRequirements(String keyRequirements) {
		this.keyRequirements = keyRequirements;
	}

	public String getIdealCandidate() {
		return idealCandidate;
	}

	public void setIdealCandidate(String idealCandidate) {
		this.idealCandidate = idealCandidate;
	}

	public String getEstimatedComplexity() {
		return estimatedComplexity;
	}

	public void setEstimatedComplexity(String estimatedComplexity) {
		this.estimatedComplexity = estimatedComplexity;
	}

	public java.util.List<String> getSuggestedSkills() {
		return suggestedSkills;
	}

	public void setSuggestedSkills(java.util.List<String> suggestedSkills) {
		this.suggestedSkills = suggestedSkills;
	}

	public ProjectSummaryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectSummaryDTO(Long projectId, String summary, String keyRequirements, String idealCandidate,
			String estimatedComplexity, List<String> suggestedSkills) {
		super();
		this.projectId = projectId;
		this.summary = summary;
		this.keyRequirements = keyRequirements;
		this.idealCandidate = idealCandidate;
		this.estimatedComplexity = estimatedComplexity;
		this.suggestedSkills = suggestedSkills;
	}
    
    
    
    
}