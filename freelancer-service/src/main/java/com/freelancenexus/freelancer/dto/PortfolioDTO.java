package com.freelancenexus.freelancer.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for representing a portfolio item.
 * Includes details such as title, description, URLs, and completion date.
 */

public class PortfolioDTO {
    
    /**
     * The unique identifier of the portfolio item.
     */
    private Long id;
    
    /**
     * The title of the portfolio item.
     * Must not be blank and cannot exceed 255 characters.
     */
    @NotBlank(message = "Portfolio title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;
    
    /**
     * A description of the portfolio item.
     * Cannot exceed 5000 characters.
     */
    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;
    
    /**
     * The URL of the project associated with the portfolio item.
     * Cannot exceed 500 characters.
     */
    @Size(max = 500, message = "Project URL must not exceed 500 characters")
    private String projectUrl;
    
    /**
     * The URL of the image associated with the portfolio item.
     * Cannot exceed 500 characters.
     */
    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    private String imageUrl;
    
    /**
     * A description of the technologies used in the portfolio item.
     * Cannot exceed 1000 characters.
     */
    @Size(max = 1000, message = "Technologies used must not exceed 1000 characters")
    private String technologiesUsed;
    
    /**
     * The completion date of the portfolio item.
     * Must be in the past or present.
     */
    @PastOrPresent(message = "Completion date cannot be in the future")
    private LocalDate completionDate;
    
    /**
     * The timestamp when the portfolio item was created.
     */
    private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getProjectUrl() {
		return projectUrl;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTechnologiesUsed() {
		return technologiesUsed;
	}

	public void setTechnologiesUsed(String technologiesUsed) {
		this.technologiesUsed = technologiesUsed;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public PortfolioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PortfolioDTO(Long id,
			@NotBlank(message = "Portfolio title is required") @Size(max = 255, message = "Title must not exceed 255 characters") String title,
			@Size(max = 5000, message = "Description must not exceed 5000 characters") String description,
			@Size(max = 500, message = "Project URL must not exceed 500 characters") String projectUrl,
			@Size(max = 500, message = "Image URL must not exceed 500 characters") String imageUrl,
			@Size(max = 1000, message = "Technologies used must not exceed 1000 characters") String technologiesUsed,
			@PastOrPresent(message = "Completion date cannot be in the future") LocalDate completionDate,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.projectUrl = projectUrl;
		this.imageUrl = imageUrl;
		this.technologiesUsed = technologiesUsed;
		this.completionDate = completionDate;
		this.createdAt = createdAt;
	}
    
    
    
}