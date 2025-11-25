package com.freelancenexus.userservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelancenexus.userservice.dto.ProposalDTO;
import com.freelancenexus.userservice.dto.ProposalSubmitDTO;
import com.freelancenexus.userservice.model.Freelancer;
import com.freelancenexus.userservice.model.ProposalStatus;
import com.freelancenexus.userservice.repository.FreelancerRepository;
import com.freelancenexus.userservice.repository.ProposalRepository;
import com.freelancenexus.userservice.service.ProposalService;

import jakarta.validation.Valid;

/**
 * ProposalController
 *
 * <p>REST API controller for proposal management in the Project Service.
 * Provides endpoints for freelancers to submit proposals, clients to view and accept/reject proposals,
 * and both roles to retrieve proposal information. Implements role-based access control for sensitive operations.</p>
 *
 * <p>Endpoints are categorized by operation type:
 * <ul>
 *   <li><strong>Submission</strong> — freelancers submit proposals for projects</li>
 *   <li><strong>Retrieval</strong> — view proposals by project, freelancer, or id</li>
 *   <li><strong>Ranking</strong> — clients can request AI-ranked proposals</li>
 *   <li><strong>Decision</strong> — clients accept or reject proposals</li>
 * </ul>
 * </p>
 *
 * @since 1.0
 */
@RestController
@RequestMapping("/api")
public class ProposalController {
	@Autowired
    private ProposalService proposalService;
	@Autowired
    private  ProposalRepository proposalRepository;
	@Autowired
	private FreelancerRepository freelancerRepository;
    private final Logger log= LoggerFactory.getLogger(ProposalController.class);
   

	/**
     * Submit a new proposal for a project.
     *
     * <p>Requires FREELANCER role. Allows a freelancer to submit their proposal
     * (including bid amount, cover letter, etc.) for a specific project.</p>
     *
     * @param projectId the unique identifier of the project
     * @param submitDTO proposal submission details
     * @return ResponseEntity with HTTP 201 (Created) status and the created {@link ProposalDTO}
     */
    @PostMapping("/projects/{projectId}/proposals")
    @PreAuthorize("hasRole('FREELANCER')")
    public ResponseEntity<ProposalDTO> submitProposal(
            @PathVariable Long projectId,
            @Valid @RequestBody ProposalSubmitDTO submitDTO) {
        log.info("POST /api/projects/{}/proposals - Submitting proposal", projectId);
        ProposalDTO proposal = proposalService.submitProposal(projectId, submitDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(proposal);
    }

    /**
     * Retrieve all proposals for a specific project.
     *
     * <p>Requires CLIENT role. Optionally returns AI-ranked proposals if the
     * {@code ranked} query parameter is set to true.</p>
     *
     * @param projectId the unique identifier of the project
     * @param ranked if true, returns proposals ranked by AI; otherwise returns in submission order
     * @return ResponseEntity with HTTP 200 (OK) status and a list of {@link ProposalDTO}
     */
    @GetMapping("/projects/{projectId}/proposals")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<ProposalDTO>> getProjectProposals(
            @PathVariable Long projectId,
            @RequestParam(defaultValue = "false") boolean ranked) {
        log.info("GET /api/projects/{}/proposals - Fetching proposals", projectId);
        
        List<ProposalDTO> proposals;
        if (ranked) {
            proposals = proposalService.getRankedProposalsByProjectId(projectId);
        } else {
            proposals = proposalService.getProposalsByProjectId(projectId);
        }
        
        return ResponseEntity.ok(proposals);
    }

    /**
     * Retrieve a specific proposal by id.
     *
     * <p>Requires CLIENT or FREELANCER role. Returns detailed proposal information.</p>
     *
     * @param id the unique identifier of the proposal
     * @return ResponseEntity with HTTP 200 (OK) status and the {@link ProposalDTO}
     */
    @GetMapping("/proposal/{id}")
    @PreAuthorize("hasAnyRole('CLIENT', 'FREELANCER')")
    public ResponseEntity<ProposalDTO> getProposal(@PathVariable Long id) {
        log.info("GET /api/proposals/{} - Fetching proposal", id);
        ProposalDTO proposal = proposalService.getProposalById(id);
        return ResponseEntity.ok(proposal);
    }

    /**
     * Retrieve all proposals submitted by a specific freelancer.
     *
     * <p>Requires FREELANCER role. Returns the freelancer's submission history.</p>
     *
     * @param freelancerId the unique identifier of the freelancer
     * @return ResponseEntity with HTTP 200 (OK) status and a list of {@link ProposalDTO}
     */
    @GetMapping("/proposals/freelancer/{freelancerId}")
    @PreAuthorize("hasRole('FREELANCER')")
    public ResponseEntity<List<ProposalDTO>> getFreelancerProposals(@PathVariable Long freelancerId) {
        log.info("GET /api/proposals/freelancer/{} - Fetching freelancer proposals", freelancerId);
        List<ProposalDTO> proposals = proposalService.getProposalsByFreelancerId(freelancerId);
        return ResponseEntity.ok(proposals);
    }

    /**
     * Accept a proposal for a project.
     *
     * <p>Requires CLIENT role. Only the client who created the project can accept proposals.
     * Accepting a proposal typically results in the freelancer being assigned to the project.</p>
     *
     * @param id the unique identifier of the proposal to accept
     * @return ResponseEntity with HTTP 200 (OK) status and the updated {@link ProposalDTO}
     */
    @PutMapping("/proposals/{id}/accept")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ProposalDTO> acceptProposal(@PathVariable Long id) {
        log.info("PUT /api/proposals/{}/accept - Accepting proposal", id);
        ProposalDTO proposal = proposalService.acceptProposal(id);
        return ResponseEntity.ok(proposal);
    }

    /**
     * Reject a proposal for a project.
     *
     * <p>Requires CLIENT role. Only the client who created the project can reject proposals.
     * Rejected proposals will not be further considered for the project.</p>
     *
     * @param id the unique identifier of the proposal to reject
     * @return ResponseEntity with HTTP 200 (OK) status and the updated {@link ProposalDTO}
     */
    @PutMapping("/proposals/{id}/reject")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ProposalDTO> rejectProposal(@PathVariable Long id) {
        log.info("PUT /api/proposals/{}/reject - Rejecting proposal", id);
        ProposalDTO proposal = proposalService.rejectProposal(id);
        return ResponseEntity.ok(proposal);
    }

    @GetMapping("/freelancers/{freelancerId}/stats")
    public ResponseEntity<Map<String, Object>> getFreelancerStats(@PathVariable Long freelancerId) {
     
        long total = proposalRepository.countByFreelancerId(freelancerId);
        long accepted = proposalRepository.countByFreelancerIdAndStatus(freelancerId, ProposalStatus.ACCEPTED);
        long pending = proposalRepository.countByFreelancerIdAndStatus(freelancerId, ProposalStatus.PENDING);
     
        // ⭐ Load freelancer to get rating from DB
        Freelancer freelancer = freelancerRepository.findById(freelancerId).orElse(null);
     
        BigDecimal rating = BigDecimal.ZERO;
        if (freelancer != null && freelancer.getAverageRating() != null) {
            rating = freelancer.getAverageRating();
        }
     
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProposals", total);
        stats.put("acceptedProposals", accepted);
        stats.put("pendingProposals", pending);
        stats.put("totalEarnings", freelancer != null ? freelancer.getTotalEarnings() : 0);
        stats.put("rating", rating);
        stats.put("profileCompletion", 70);
     
        return ResponseEntity.ok(stats);
    }
    
    @PutMapping("/proposals/{id}/withdraw")
    @PreAuthorize("hasRole('FREELANCER')")
    public ResponseEntity<ProposalDTO> withdrawProposal(@PathVariable Long id) {
        log.info("PUT /api/proposals/{}/withdraw - Withdrawing proposal", id);
        ProposalDTO proposal = proposalService.withdrawProposal(id);
        return ResponseEntity.ok(proposal);
    }
    /**
     * Global exception handler for RuntimeException in this controller.
     *
     * @param ex the thrown {@link RuntimeException}
     * @return ResponseEntity with HTTP 400 (Bad Request) status and error message
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        log.error("Error in ProposalController", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}