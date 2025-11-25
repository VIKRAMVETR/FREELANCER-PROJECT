package com.freelancenexus.freelancer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity representing a rating given to a freelancer.
 */
@Entity
@Table(name = "ratings")
public class Rating {
    
    /**
     * The unique identifier of the rating.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * The freelancer associated with this rating.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freelancer_id", nullable = false)
    private Freelancer freelancer;
    
    /**
     * The ID of the client who provided the rating.
     */
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    
    /**
     * The ID of the project associated with the rating.
     */
    @Column(name = "project_id")
    private Long projectId;
    
    /**
     * The rating value.
     */
    @Column(nullable = false)
    private Integer rating;
    
    /**
     * The review text provided by the client.
     */
    @Column(columnDefinition = "TEXT")
    private String review;
    
    /**
     * The timestamp when the rating was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Sets the creation timestamp before persisting the entity.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

	public Rating(Long id, Freelancer freelancer, Long clientId, Long projectId, Integer rating, String review,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.freelancer = freelancer;
		this.clientId = clientId;
		this.projectId = projectId;
		this.rating = rating;
		this.review = review;
		this.createdAt = createdAt;
	}

	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Freelancer getFreelancer() {
		return freelancer;
	}

	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
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
    
	
    
}