package com.freelancenexus.userservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancenexus.userservice.model.Skill;
import com.freelancenexus.userservice.model.Skill.ProficiencyLevel;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    
    List<Skill> findByFreelancerId(Long freelancerId);
    
    List<Skill> findByFreelancerIdAndProficiencyLevel(Long freelancerId, ProficiencyLevel level);
    
    boolean existsByFreelancerIdAndSkillNameIgnoreCase(Long freelancerId, String skillName);
    
    void deleteByFreelancerId(Long freelancerId);
}