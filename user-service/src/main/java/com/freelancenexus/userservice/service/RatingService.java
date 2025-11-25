package com.freelancenexus.userservice.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freelancenexus.userservice.dto.RatingDTO;
import com.freelancenexus.userservice.exception.DuplicateRatingException;
import com.freelancenexus.userservice.exception.ResourceNotFoundException;
import com.freelancenexus.userservice.model.Freelancer;
import com.freelancenexus.userservice.model.Project;
import com.freelancenexus.userservice.model.ProjectStatus;
import com.freelancenexus.userservice.model.Rating;
import com.freelancenexus.userservice.repository.FreelancerRepository;
import com.freelancenexus.userservice.repository.ProjectRepository;
import com.freelancenexus.userservice.repository.RatingRepository;
@Service
public class RatingService {
	private final Logger log= LoggerFactory.getLogger(RatingService.class);
    private final RatingRepository ratingRepository;
    private final FreelancerRepository freelancerRepository;
    private final FreelancerService freelancerService;
    private final ProjectRepository projectRepository;
    
    public RatingService(RatingRepository ratingRepository, FreelancerRepository freelancerRepository,
			FreelancerService freelancerService, ProjectRepository projectRepository ) {
		super();
		this.ratingRepository = ratingRepository;
		this.freelancerRepository = freelancerRepository;
		this.freelancerService = freelancerService;
		this.projectRepository = projectRepository;
	}

	@Transactional
	public RatingDTO addRating(Long freelancerId, RatingDTO ratingDTO) {
	    log.info("Adding rating for freelancer ID: {} by client ID: {}", freelancerId, ratingDTO.getClientId());
	 
	    Freelancer freelancer = freelancerRepository.findById(freelancerId)
	            .orElseThrow(() -> new ResourceNotFoundException("Freelancer not found with ID: " + freelancerId));
	 
	    // ⭐ CHECK PROJECT STATUS BEFORE RATING
	    if (ratingDTO.getProjectId() == null) {
	        throw new RuntimeException("Project ID is required to give rating");
	    }
	 
	    Project project = projectRepository.findById(ratingDTO.getProjectId())
	            .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
	 
	    // ⭐ ONLY ALLOW RATING WHEN COMPLETED
	    if (project.getStatus() != ProjectStatus.COMPLETED) {
	        throw new RuntimeException("You can rate only after project completion");
	    }
	 
	    // ⭐ Prevent duplicate rating
	    if (ratingRepository.existsByFreelancerIdAndClientIdAndProjectId(
	            freelancerId, ratingDTO.getClientId(), ratingDTO.getProjectId())) {
	        throw new DuplicateRatingException("Rating already exists for this project");
	    }
	 
	    Rating rating = mapToEntity(ratingDTO);
	    freelancer.addRating(rating);
	 
	    Rating saved = ratingRepository.save(rating);
	    log.info("Rating added with ID: {}", saved.getId());
	 
	    freelancerService.updateFreelancerStats(freelancerId);
	 
	    return mapToDTO(saved);
	}
    
    @Transactional(readOnly = true)
    public List<RatingDTO> getFreelancerRatings(Long freelancerId) {
        log.info("Fetching ratings for freelancer ID: {}", freelancerId);
        
        if (!freelancerRepository.existsById(freelancerId)) {
            throw new ResourceNotFoundException("Freelancer not found with ID: " + freelancerId);
        }
        
        List<Rating> ratings = ratingRepository.findByFreelancerIdOrderByCreatedAtDesc(freelancerId);
        
        return ratings.stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public BigDecimal getAverageRating(Long freelancerId) {
        log.info("Calculating average rating for freelancer ID: {}", freelancerId);
        
        BigDecimal average = ratingRepository.calculateAverageRating(freelancerId);
        return average != null ? average : BigDecimal.ZERO;
    }
    
    @Transactional(readOnly = true)
    public long getRatingCount(Long freelancerId) {
        return ratingRepository.countByFreelancerId(freelancerId);
    }
    
    private RatingDTO mapToDTO(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());
        dto.setClientId(rating.getClientId());
        dto.setProjectId(rating.getProjectId());
        dto.setRating(rating.getRating());
        dto.setReview(rating.getReview());
        dto.setCreatedAt(rating.getCreatedAt());
        return dto;
    }
    
    private Rating mapToEntity(RatingDTO dto) {
        Rating rating = new Rating();
        rating.setClientId(dto.getClientId());
        rating.setProjectId(dto.getProjectId());
        rating.setRating(dto.getRating());
        rating.setReview(dto.getReview());
        return rating;
    }
}