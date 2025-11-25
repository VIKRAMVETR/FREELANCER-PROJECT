package com.freelancenexus.freelancer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity representing a skill associated with a freelancer.
 */
@Entity
@Table(name = "skills")
public class Skill {
    
    /**
     * The unique identifier of the skill.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * The freelancer associated with this skill.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freelancer_id", nullable = false)
    private Freelancer freelancer;
    
    /**
     * The name of the skill.
     */
    @Column(name = "skill_name", nullable = false, length = 100)
    private String skillName;
    
    /**
     * The proficiency level of the skill.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "proficiency_level", length = 50)
    private ProficiencyLevel proficiencyLevel;
    
    /**
     * The number of years of experience with the skill.
     */
    @Column(name = "years_experience")
    private Integer yearsExperience;
    
    /**
     * Enum representing the proficiency levels for a skill.
     */
    public enum ProficiencyLevel {
        BEGINNER,
        INTERMEDIATE,
        EXPERT
    }

	public Skill(Long id, Freelancer freelancer, String skillName, ProficiencyLevel proficiencyLevel,
			Integer yearsExperience) {
		super();
		this.id = id;
		this.freelancer = freelancer;
		this.skillName = skillName;
		this.proficiencyLevel = proficiencyLevel;
		this.yearsExperience = yearsExperience;
	}

	public Skill() {
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

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public ProficiencyLevel getProficiencyLevel() {
		return proficiencyLevel;
	}

	public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
		this.proficiencyLevel = proficiencyLevel;
	}

	public Integer getYearsExperience() {
		return yearsExperience;
	}

	public void setYearsExperience(Integer yearsExperience) {
		this.yearsExperience = yearsExperience;
	}
    
    
}