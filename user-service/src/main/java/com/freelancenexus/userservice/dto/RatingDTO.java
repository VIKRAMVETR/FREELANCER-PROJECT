package com.freelancenexus.userservice.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for representing a rating.
 * Includes details such as client ID, project ID, rating value, and review.
 */

public class RatingDTO {
    
    /**
     * The unique identifier of the rating.
     */
    private Long id;
    
    /**
     * The ID of the client who provided the rating.
     * Must not be null.
     */
    @NotNull(message = "Client ID is required")
    private Long clientId;
    
    /**
     * The ID of the project associated with the rating.
     */
    private Long projectId;
    
    /**
     * The rating value.
     * Must be between 1 and 5.
     */
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private Integer rating;
    
    /**
     * The review text provided by the client.
     * Cannot exceed 2000 characters.
     */
    @Size(max = 2000, message = "Review must not exceed 2000 characters")
    private String review;
    
    /**
     * The timestamp when the rating was created.
     */
    private LocalDateTime createdAt;

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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public RatingDTO(Long id, @NotNull(message = "Client ID is required") Long clientId, Long projectId,
			@NotNull(message = "Rating is required") @Min(value = 1, message = "Rating must be at least 1") @Max(value = 5, message = "Rating must not exceed 5") Integer rating,
			@Size(max = 2000, message = "Review must not exceed 2000 characters") String review,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.projectId = projectId;
		this.rating = rating;
		this.review = review;
		this.createdAt = createdAt;
	}

	public RatingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}