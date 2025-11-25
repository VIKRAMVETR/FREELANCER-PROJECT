package com.freelancenexus.userservice.dto;



import com.freelancenexus.userservice.model.Skill.ProficiencyLevel;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for representing a skill.
 * Includes details such as skill name, proficiency level, and years of experience.
 */
public class SkillDTO {
    
    /**
     * The unique identifier of the skill.
     */
    private Long id;
    
    /**
     * The name of the skill.
     * Must not be blank and cannot exceed 100 characters.
     */
    @NotBlank(message = "Skill name is required")
    @Size(max = 100, message = "Skill name must not exceed 100 characters")
    private String skillName;
    
    /**
     * The proficiency level of the skill.
     * Must not be null.
     */
    @NotNull(message = "Proficiency level is required")
    private ProficiencyLevel proficiencyLevel;
    
    /**
     * The number of years of experience with the skill.
     * Must be a non-negative value and cannot exceed 50 years.
     */
    @Min(value = 0, message = "Years of experience cannot be negative")
    @Max(value = 50, message = "Years of experience seems too high")
    private Integer yearsExperience;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public SkillDTO(Long id,
			@NotBlank(message = "Skill name is required") @Size(max = 100, message = "Skill name must not exceed 100 characters") String skillName,
			@NotNull(message = "Proficiency level is required") ProficiencyLevel proficiencyLevel,
			@Min(value = 0, message = "Years of experience cannot be negative") @Max(value = 50, message = "Years of experience seems too high") Integer yearsExperience) {
		super();
		this.id = id;
		this.skillName = skillName;
		this.proficiencyLevel = proficiencyLevel;
		this.yearsExperience = yearsExperience;
	}

	public SkillDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SkillDTO [id=" + id + ", skillName=" + skillName + ", proficiencyLevel=" + proficiencyLevel
				+ ", yearsExperience=" + yearsExperience + "]";
	}
    
    
}