package com.freelancenexus.freelancer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

/**
 * Entity representing a freelancer.
 */
@Entity
@Table(name = "freelancers")
public class Freelancer {
    
    /**
     * The unique identifier of the freelancer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * The user ID associated with the freelancer.
     */
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;
    
    /**
     * The title or headline of the freelancer profile.
     */
    @Column(nullable = false, length = 255)
    private String title;
    
    /**
     * The biography or description of the freelancer.
     */
    @Column(columnDefinition = "TEXT")
    private String bio;
    
    /**
     * The hourly rate of the freelancer.
     */
    @Column(name = "hourly_rate", precision = 10, scale = 2)
    private BigDecimal hourlyRate;
    
    /**
     * The availability status of the freelancer.
     */
    @Column(length = 50)
    private String availability;
    
    /**
     * The total earnings of the freelancer.
     */
    @Column(name = "total_earnings", precision = 12, scale = 2)
    private BigDecimal totalEarnings = BigDecimal.ZERO;
    
    /**
     * The number of completed projects by the freelancer.
     */
    @Column(name = "completed_projects")
    private Integer completedProjects = 0;
    
    /**
     * The average rating of the freelancer.
     */
    @Column(name = "average_rating", precision = 3, scale = 2)
    private BigDecimal averageRating = BigDecimal.ZERO;
    
    /**
     * The list of skills associated with the freelancer.
     */
    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();
    
    /**
     * The list of portfolio items associated with the freelancer.
     */
    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Portfolio> portfolios = new ArrayList<>();
    
    /**
     * The list of ratings received by the freelancer.
     */
    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();
    
    /**
     * The timestamp when the freelancer profile was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * The timestamp when the freelancer profile was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    /**
     * Sets the creation and update timestamps before persisting the entity.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    /**
     * Sets the update timestamp before updating the entity.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    /**
     * Adds a skill to the freelancer.
     *
     * @param skill the skill to add
     */
    public void addSkill(Skill skill) {
        skills.add(skill);
        skill.setFreelancer(this);
    }
    
    /**
     * Removes a skill from the freelancer.
     *
     * @param skill the skill to remove
     */
    public void removeSkill(Skill skill) {
        skills.remove(skill);
        skill.setFreelancer(null);
    }
    
    /**
     * Adds a portfolio item to the freelancer.
     *
     * @param portfolio the portfolio item to add
     */
    public void addPortfolio(Portfolio portfolio) {
        portfolios.add(portfolio);
        portfolio.setFreelancer(this);
    }
    
    /**
     * Removes a portfolio item from the freelancer.
     *
     * @param portfolio the portfolio item to remove
     */
    public void removePortfolio(Portfolio portfolio) {
        portfolios.remove(portfolio);
        portfolio.setFreelancer(null);
    }
    
    /**
     * Adds a rating to the freelancer.
     *
     * @param rating the rating to add
     */
    public void addRating(Rating rating) {
        ratings.add(rating);
        rating.setFreelancer(this);
    }

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

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Portfolio> getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(List<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
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

	public Freelancer(Long id, Long userId, String title, String bio, BigDecimal hourlyRate, String availability,
			BigDecimal totalEarnings, Integer completedProjects, BigDecimal averageRating, List<Skill> skills,
			List<Portfolio> portfolios, List<Rating> ratings, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
		this.ratings = ratings;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Freelancer() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}