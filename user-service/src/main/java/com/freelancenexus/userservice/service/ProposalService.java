package com.freelancenexus.userservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freelancenexus.userservice.dto.FreelancerDTO;
import com.freelancenexus.userservice.dto.ProposalDTO;
import com.freelancenexus.userservice.dto.ProposalSubmitDTO;
import com.freelancenexus.userservice.model.Project;
import com.freelancenexus.userservice.model.ProjectStatus;
import com.freelancenexus.userservice.model.Proposal;
import com.freelancenexus.userservice.model.ProposalStatus;
import com.freelancenexus.userservice.model.Skill;
import com.freelancenexus.userservice.model.User;
import com.freelancenexus.userservice.repository.FreelancerRepository;
import com.freelancenexus.userservice.repository.ProjectRepository;
import com.freelancenexus.userservice.repository.ProposalRepository;
import com.freelancenexus.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for managing proposals in the project service. Handles
 * operations such as submitting, accepting, rejecting, and retrieving
 * proposals.
 */
@Service
@RequiredArgsConstructor
public class ProposalService {
	@Autowired
	private ProposalRepository proposalRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private FreelancerService freelancerService;
	private final Logger log = LoggerFactory.getLogger(ProposalService.class);

	@Autowired
	private FreelancerRepository freelancerRepository;
    
	  /**
     * Jackson ObjectMapper used to serialize/deserialize skill lists.
     */
	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
	private UserRepository userRepository;

	@Value("${rabbitmq.exchange.project}")
	private String projectExchange;

	@Value("${rabbitmq.routing.proposal.submitted}")
	private String proposalSubmittedRoutingKey;

	/**
	 * Submits a proposal for a given project.
	 *
	 * @param projectId the ID of the project for which the proposal is submitted
	 * @param submitDTO the data transfer object containing proposal details
	 * @return the submitted proposal as a DTO
	 * @throws RuntimeException if the project does not exist, is not open, or if
	 *                          the freelancer has already submitted a proposal
	 */
	@Transactional
	public ProposalDTO submitProposal(Long projectId, ProposalSubmitDTO submitDTO) {
		log.info("Submitting proposal for project ID: {} by freelancer: {}", projectId, submitDTO.getFreelancerId());

		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));

		if (project.getStatus() != ProjectStatus.OPEN) {
			throw new RuntimeException("Project is not accepting proposals");
		}

		if (proposalRepository.existsByProjectIdAndFreelancerId(projectId, submitDTO.getFreelancerId())) {
			throw new RuntimeException("Freelancer already submitted a proposal for this project");
		}

		Proposal proposal = new Proposal();
		proposal.setProject(project);
		proposal.setFreelancerId(submitDTO.getFreelancerId());
		proposal.setCoverLetter(submitDTO.getCoverLetter());
		proposal.setProposedBudget(submitDTO.getProposedBudget());
		proposal.setDeliveryDays(submitDTO.getDeliveryDays());
		proposal.setStatus(ProposalStatus.PENDING);

		Proposal savedProposal = proposalRepository.save(proposal);
		log.info("Proposal created with ID: {}", savedProposal.getId());

		publishProposalSubmittedEvent(savedProposal);

		return convertToDTO(savedProposal);
	}

	/**
	 * Retrieves all proposals for a specific project.
	 *
	 * @param projectId the ID of the project
	 * @return a list of proposals as DTOs
	 */
	public List<ProposalDTO> getProposalsByProjectId(Long projectId) {
	    List<Proposal> proposals = proposalRepository.findByProjectIdWithProject(projectId);
	    return proposals.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	/**
	 * Retrieves all proposals submitted by a specific freelancer.
	 *
	 * @param freelancerId the ID of the freelancer
	 * @return a list of proposals as DTOs
	 */

	public List<ProposalDTO> getProposalsByFreelancerId(Long freelancerId) {
		List<Proposal> proposals = proposalRepository.findByFreelancerIdFetchProject(freelancerId);
		return proposals.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	/**
	 * Retrieves a proposal by its ID.
	 *
	 * @param id the ID of the proposal
	 * @return the proposal as a DTO
	 * @throws RuntimeException if the proposal does not exist
	 */
	@Transactional(readOnly = true)
	public ProposalDTO getProposalById(Long id) {
		log.info("Fetching proposal by ID: {}", id);
		Proposal proposal = proposalRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + id));
		return convertToDTO(proposal);
	}

	/**
	 * Accepts a proposal and updates the project and other proposals accordingly.
	 *
	 * @param proposalId the ID of the proposal to accept
	 * @return the accepted proposal as a DTO
	 * @throws RuntimeException if the proposal does not exist or is not in pending
	 *                          status
	 */
	@Transactional
	public ProposalDTO acceptProposal(Long proposalId) {
		log.info("Accepting proposal ID: {}", proposalId);

		Proposal proposal = proposalRepository.findById(proposalId)
				.orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

		if (proposal.getStatus() != ProposalStatus.PENDING) {
			throw new RuntimeException("Proposal is not in pending status");
		}

		proposal.setStatus(ProposalStatus.ACCEPTED);

		List<Proposal> otherProposals = proposalRepository.findByProjectIdAndStatus(proposal.getProject().getId(),
				ProposalStatus.PENDING);

		otherProposals.stream().filter(p -> !p.getId().equals(proposalId)).forEach(p -> {
			p.setStatus(ProposalStatus.REJECTED);
			proposalRepository.save(p);
		});

		Proposal acceptedProposal = proposalRepository.save(proposal);

		Project project = proposal.getProject();
		project.setAssignedFreelancer(proposal.getFreelancerId());
		project.setStatus(ProjectStatus.IN_PROGRESS);
		projectRepository.save(project);

		return convertToDTO(acceptedProposal);
	}

	/**
	 * Rejects a proposal.
	 *
	 * @param proposalId the ID of the proposal to reject
	 * @return the rejected proposal as a DTO
	 * @throws RuntimeException if the proposal does not exist or is not in pending
	 *                          status
	 */
	@Transactional
	public ProposalDTO rejectProposal(Long proposalId) {
		log.info("Rejecting proposal ID: {}", proposalId);

		Proposal proposal = proposalRepository.findById(proposalId)
				.orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

		if (proposal.getStatus() != ProposalStatus.PENDING) {
			throw new RuntimeException("Proposal is not in pending status");
		}

		proposal.setStatus(ProposalStatus.REJECTED);
		Proposal rejectedProposal = proposalRepository.save(proposal);

		return convertToDTO(rejectedProposal);
	}

	/**
	 * Retrieves ranked proposals for a specific project, ordered by AI score in
	 * descending order.
	 *
	 * @param projectId the ID of the project
	 * @return a list of ranked proposals as DTOs
	 */
	public List<ProposalDTO> getRankedProposalsByProjectId(Long projectId) {
		log.info("Fetching ranked proposals for project ID: {}", projectId);
		return proposalRepository.findByProjectIdOrderByAiScoreDesc(projectId).stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Converts a Proposal entity to a ProposalDTO.
	 *
	 * @param proposal the Proposal entity
	 * @return the ProposalDTO
	 */
	
	private List<String> parseSkills(String json) {
	    try {
	        // Case 1: Valid JSON array → ["Java","Spring"]
	        return objectMapper.readValue(json, List.class);
	    } catch (Exception e) {
	        // Case 2: fallback → "Java, Spring , Docker"
	        return Arrays.stream(json.split(","))
	                .map(String::trim)
	                .filter(s -> !s.isEmpty())
	                .collect(Collectors.toList());
	    }
	}
	
	
	private ProposalDTO convertToDTO(Proposal proposal) {
		 
	    ProposalDTO dto = new ProposalDTO();
	    dto.setId(proposal.getId());
	    dto.setProjectId(proposal.getProject().getId());
	    dto.setProjectTitle(proposal.getProject().getTitle());
	    dto.setFreelancerId(proposal.getFreelancerId());
	    dto.setCoverLetter(proposal.getCoverLetter());
	    dto.setProposedBudget(proposal.getProposedBudget());
	    dto.setDeliveryDays(proposal.getDeliveryDays());
	    dto.setAiScore(proposal.getAiScore());
	    dto.setStatus(proposal.getStatus());
	    dto.setSubmittedAt(proposal.getSubmittedAt());
	    dto.setUpdatedAt(proposal.getUpdatedAt());
	    dto.setProjectDescription(proposal.getProject().getDescription());
	 
	    // ⭐ Load freelancer
	    FreelancerDTO freelancerDTO = freelancerService.getFreelancerById(proposal.getFreelancerId());
	    
	    if (freelancerDTO != null) {
	     
	        // Set freelancer name
	        User user = userRepository.findById(freelancerDTO.getUserId()).orElse(null);
	        if (user != null) {
	            dto.setFreelancerName(user.getFullName());
	        }
	     
	        // Set freelancer skills (THIS FIXES YOUR UI ISSUE)
	        dto.setFreelancerSkills(
	                freelancerDTO.getSkills() == null
	                        ? List.of()
	                        : freelancerDTO.getSkills()
	                                        .stream()
	                                        .map(s -> s.getSkillName())
	                                        .collect(Collectors.toList())
	        );
	     
	        // Set freelancer rating
	        dto.setFreelancerRating(freelancerDTO.getAverageRating());
	    }
	 
	    return dto;
	}


	@Transactional
	public ProposalDTO withdrawProposal(Long proposalId) {
		log.info("Withdrawing proposal ID: {}", proposalId);

		Proposal proposal = proposalRepository.findById(proposalId)
				.orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

		if (proposal.getStatus() != ProposalStatus.PENDING) {
			throw new RuntimeException("Only pending proposals can be withdrawn");
		}

		proposal.setStatus(ProposalStatus.WITHDRAWN);

		Proposal saved = proposalRepository.save(proposal);
		return convertToDTO(saved);
	}

	/**
	 * Publishes a proposal submitted event to RabbitMQ.
	 *
	 * @param proposal the Proposal entity
	 */
	private void publishProposalSubmittedEvent(Proposal proposal) {
		try {
			ProposalDTO dto = convertToDTO(proposal);
			rabbitTemplate.convertAndSend(projectExchange, proposalSubmittedRoutingKey, dto);
			log.info("Published proposal.submitted event for proposal ID: {}", proposal.getId());
		} catch (Exception e) {
			log.error("Error publishing proposal.submitted event", e);
		}
	}
}