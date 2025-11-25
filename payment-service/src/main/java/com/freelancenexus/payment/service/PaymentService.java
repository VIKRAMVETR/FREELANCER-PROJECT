package com.freelancenexus.payment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freelancenexus.payment.dto.PaymentHistoryDTO;
import com.freelancenexus.payment.dto.PaymentRequestDTO;
import com.freelancenexus.payment.dto.PaymentResponseDTO;
import com.freelancenexus.payment.dto.TransactionStatusDTO;
import com.freelancenexus.payment.dto.UPIPaymentDTO;
import com.freelancenexus.payment.event.PaymentCompletedEvent;
import com.freelancenexus.payment.model.Payment;
import com.freelancenexus.payment.model.PaymentStatus;
import com.freelancenexus.payment.model.TransactionHistory;
import com.freelancenexus.payment.repository.PaymentRepository;
import com.freelancenexus.payment.repository.TransactionHistoryRepository;
@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final UPIPaymentService upiPaymentService;
    private final RabbitTemplate rabbitTemplate;
    
	private final Logger log= LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(PaymentRepository paymentRepository,
			TransactionHistoryRepository transactionHistoryRepository, UPIPaymentService upiPaymentService,
			RabbitTemplate rabbitTemplate) {
		super();
		this.paymentRepository = paymentRepository;
		this.transactionHistoryRepository = transactionHistoryRepository;
		this.upiPaymentService = upiPaymentService;
		this.rabbitTemplate = rabbitTemplate;
	}

	/**
     * Initiate a new payment
     */
    @Transactional
    public PaymentResponseDTO initiatePayment(PaymentRequestDTO request) {
        log.info("Initiating payment for project: {}", request.getProjectId());
        
        // Validate UPI ID
        if (!upiPaymentService.isValidUPIId(request.getUpiId())) {
            throw new IllegalArgumentException("Invalid UPI ID format");
        }
        
        // Generate unique transaction ID
        String transactionId = generateTransactionId();
        
        // Create payment entity
        Payment payment = new Payment(transactionId, request.getProjectId(), request.getPayerId(), request.getPayeeId(), request.getAmount(),
        		request.getCurrency(), request.getPaymentMethod(), request.getUpiId(),PaymentStatus.PENDING, 
        		request.getDescription());
        
        payment = paymentRepository.save(payment);
        
        // Record transaction history
        recordTransactionHistory(payment.getId(), 
                PaymentStatus.PENDING.name(), 
                "Payment initiated");
        
        // Generate UPI payment link
        UPIPaymentDTO upiPayment = upiPaymentService.generateUPIPaymentLink(
                transactionId, 
                request.getAmount(), 
                request.getUpiId(),
                request.getCurrency()
        );
        
        log.info("Payment initiated successfully with transaction ID: {}", transactionId);
        
        return mapToResponseDTO(payment);
    }
    
    /**
     * Verify and complete payment
     */
    @Transactional
    public TransactionStatusDTO verifyPayment(String transactionId) {
        log.info("Verifying payment: {}", transactionId);
        
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + transactionId));
        
        if (payment.getStatus() != PaymentStatus.PENDING) {
            log.warn("Payment {} is not in PENDING status: {}", transactionId, payment.getStatus());
            
            return new TransactionStatusDTO(transactionId, payment.getStatus(), payment.getAmount(), payment.getCurrency(),
            		LocalDateTime.now(),"Payment is already " + payment.getStatus());
        }
        
        // Verify with UPI service
        UPIPaymentService.TransactionVerificationResult verificationResult = 
                upiPaymentService.verifyUPIPayment(transactionId);
        
        if (verificationResult.isSuccess()) {
            // Update payment status to COMPLETED
            payment.setStatus(PaymentStatus.COMPLETED);
            payment.setPaymentDate(LocalDateTime.now());
            paymentRepository.save(payment);
            
            // Record transaction history
            recordTransactionHistory(payment.getId(), 
                    PaymentStatus.COMPLETED.name(), 
                    "Payment completed successfully");
            
            // Publish payment completed event
            publishPaymentCompletedEvent(payment);
            
            log.info("Payment completed successfully: {}", transactionId);
            
            return new TransactionStatusDTO(transactionId, PaymentStatus.COMPLETED,payment.getAmount(), payment.getCurrency(),
            		payment.getPaymentDate(), "Payment completed successfully");
            		
        } else {
            // Update payment status to FAILED
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            
            // Record transaction history
            recordTransactionHistory(payment.getId(), 
                    PaymentStatus.FAILED.name(), 
                    verificationResult.getMessage());
            
            log.warn("Payment verification failed: {}", transactionId);
            
            return  new TransactionStatusDTO(transactionId, PaymentStatus.FAILED, payment.getAmount(), payment.getCurrency(),
            		LocalDateTime.now(), verificationResult.getMessage());
        }
    }
    
    /**
     * Get payment by ID
     */
    public PaymentResponseDTO getPaymentById(Long id) {
        log.info("Fetching payment by ID: {}", id);
        
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + id));
        
        return mapToResponseDTO(payment);
    }
    
    /**
     * Get payment by transaction ID
     */
    public PaymentResponseDTO getPaymentByTransactionId(String transactionId) {
        log.info("Fetching payment by transaction ID: {}", transactionId);
        
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + transactionId));
        
        return mapToResponseDTO(payment);
    }
    
    /**
     * Get all payments for a project
     */
    public List<PaymentResponseDTO> getPaymentsByProject(Long projectId) {
        log.info("Fetching payments for project: {}", projectId);
        
        return paymentRepository.findByProjectId(projectId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Get payment history for a user (both as payer and payee)
     */
    public List<PaymentResponseDTO> getUserPaymentHistory(Long userId) {
        log.info("Fetching payment history for user: {}", userId);
        
        return paymentRepository.findByPayerIdOrPayeeId(userId, userId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Refund a payment
     */
    @Transactional
    public PaymentResponseDTO refundPayment(Long paymentId, String reason) {
        log.info("Initiating refund for payment: {}", paymentId);
        
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + paymentId));
        
        if (payment.getStatus() != PaymentStatus.COMPLETED) {
            throw new IllegalStateException("Can only refund completed payments");
        }
        
        // Initiate UPI refund
        UPIPaymentService.RefundResult refundResult = upiPaymentService.initiateRefund(payment.getTransactionId(), payment.getAmount());
        
        if (!refundResult.isSuccess()) {
            throw new RuntimeException("Refund failed: " + refundResult.getMessage());
        }
        
        // Update payment status to REFUNDED
        payment.setStatus(PaymentStatus.REFUNDED);
        payment = paymentRepository.save(payment);
        
        // Record transaction history
        recordTransactionHistory(payment.getId(), 
                PaymentStatus.REFUNDED.name(), 
                "Payment refunded. Reason: " + reason + ". Refund ID: " + refundResult.getRefundTransactionId());
        
        log.info("Payment refunded successfully: {}", paymentId);
        
        return mapToResponseDTO(payment);
    }
    
    /**
     * Get transaction history for a payment
     */
    public List<PaymentHistoryDTO> getTransactionHistory(Long paymentId) {
        log.info("Fetching transaction history for payment: {}", paymentId);
        
        return transactionHistoryRepository.findByPaymentIdOrderByChangedAtDesc(paymentId).stream()
                .map(this::mapToHistoryDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Get all transaction history for a user
     */
    public List<PaymentHistoryDTO> getUserTransactionHistory(Long userId) {
        log.info("Fetching transaction history for user: {}", userId);
        
        // Get all payment IDs for user
        List<Long> paymentIds = paymentRepository.findByPayerIdOrPayeeId(userId, userId).stream()
                .map(Payment::getId)
                .collect(Collectors.toList());
        
        if (paymentIds.isEmpty()) {
            return List.of();
        }
        
        return transactionHistoryRepository.findByPaymentIdInOrderByChangedAtDesc(paymentIds).stream()
                .map(this::mapToHistoryDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Record transaction history
     */
    private void recordTransactionHistory(Long paymentId, String statusChange, String notes) {
        TransactionHistory history = new TransactionHistory(paymentId,statusChange,notes);
                
        
        transactionHistoryRepository.save(history);
        log.debug("Transaction history recorded for payment: {}", paymentId);
    }
    
    /**
     * Publish payment completed event to RabbitMQ
     */
    private void publishPaymentCompletedEvent(Payment payment) {
        try {
            PaymentCompletedEvent event = new PaymentCompletedEvent(payment.getId(), payment.getTransactionId(), payment.getProjectId(), payment.getPayerId(), payment.getPayeeId(),
            		payment.getAmount(), payment.getCurrency(), payment.getPaymentDate());
            
            rabbitTemplate.convertAndSend("payment.completed", event);
            
            log.info("Payment completed event published for transaction: {}", payment.getTransactionId());
        } catch (Exception e) {
            log.error("Failed to publish payment completed event: {}", e.getMessage(), e);
            // Don't throw exception - payment is already completed
        }
    }
    
    /**
     * Generate unique transaction ID
     */
    private String generateTransactionId() {
        return "TXN-" + UUID.randomUUID().toString().toUpperCase();
    }
    
    /**
     * Map Payment entity to PaymentResponseDTO
     */
    private PaymentResponseDTO mapToResponseDTO(Payment payment) {
        return new PaymentResponseDTO(payment.getId(),payment.getTransactionId(), payment.getProjectId(), payment.getPayerId(), payment.getPayeeId(),
        		payment.getAmount(), payment.getCurrency(), payment.getPaymentMethod(), payment.getUpiId(), payment.getStatus(),
        		payment.getPaymentDate(), payment.getDescription(), payment.getCreatedAt(), payment.getUpdatedAt());
    }
    
    /**
     * Map TransactionHistory entity to PaymentHistoryDTO
     */
    private PaymentHistoryDTO mapToHistoryDTO(TransactionHistory history) {
        return new PaymentHistoryDTO(history.getId(), history.getPaymentId(),history.getStatusChange(), history.getNotes(), history.getChangedAt());
    }
}