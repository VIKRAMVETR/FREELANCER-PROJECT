package com.freelancenexus.freelancer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity representing a portfolio item associated with a freelancer.
 */
@Entity
@Table(name = "portfolios")

public class Portfolio {
    
    /**
     * The unique identifier of the portfolio item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * The freelancer associated with this portfolio item.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freelancer_id", nullable = false)
    private Freelancer freelancer;
    
    /**
     * The title of the portfolio item.
     */
    @Column(nullable = false, length = 255)
    private String title;
    
    /**
     * A description of the portfolio item.
     */
    @Column(columnDefinition = "TEXT")
    private String description;
    
    /**
     * The URL of the project associated with the portfolio item.
     */
    @Column(name = "project_url", length = 500)
    private String projectUrl;
    
    /**
     * The URL of the image associated with the portfolio item.
     */
    @Column(name = "image_url", length = 500)
    private String imageUrl;
    
    /**
     * A description of the technologies used in the portfolio item.
     */
    @Column(name = "technologies_used", columnDefinition = "TEXT")
    private String technologiesUsed;
    
    /**
     * The completion date of the portfolio item.
     */
    @Column(name = "completion_date")
    private LocalDate completionDate;
    
    /**
     * The timestamp when the portfolio item was created.
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

	public Portfolio(Long id, Freelancer freelancer, String title, String description, String projectUrl,
			String imageUrl, String technologiesUsed, LocalDate completionDate, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.freelancer = freelancer;
		this.title = title;
		this.description = description;
		this.projectUrl = projectUrl;
		this.imageUrl = imageUrl;
		this.technologiesUsed = technologiesUsed;
		this.completionDate = completionDate;
		this.createdAt = createdAt;
	}

	public Portfolio() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}