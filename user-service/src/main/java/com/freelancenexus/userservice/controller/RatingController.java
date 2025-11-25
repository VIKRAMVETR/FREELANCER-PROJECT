package com.freelancenexus.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancenexus.userservice.dto.RatingDTO;
import com.freelancenexus.userservice.service.RatingService;

import jakarta.validation.Valid;
/**
 * REST controller for managing freelancer ratings.
 * Provides endpoints for adding and retrieving ratings.
 */
@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	private final Logger log= LoggerFactory.getLogger(RatingController.class);
    private final RatingService ratingService;

    
    public RatingController(RatingService ratingService) {
		super();
		this.ratingService = ratingService;
	}

	/**
     * Adds a rating for a freelancer.
     *
     * @param id the ID of the freelancer
     * @param ratingDTO the rating to be added
     * @return the created rating
     */
    // Only clients can add ratings (after project completion)
    @PostMapping("/{id}/ratings")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<RatingDTO> addRating(
            @PathVariable Long id,
            @Valid @RequestBody RatingDTO ratingDTO) {
        log.info("REST request to add rating for freelancer ID: {}", id);
        RatingDTO created = ratingService.addRating(id, ratingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Retrieves all ratings for a freelancer.
     *
     * @param id the ID of the freelancer
     * @return the list of ratings
     */
    // Anyone authenticated can view ratings
    @GetMapping("/{id}/ratings")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RatingDTO>> getFreelancerRatings(@PathVariable Long id) {
        log.info("REST request to get ratings for freelancer ID: {}", id);
        List<RatingDTO> ratings = ratingService.getFreelancerRatings(id);
        return ResponseEntity.ok(ratings);
    }
}