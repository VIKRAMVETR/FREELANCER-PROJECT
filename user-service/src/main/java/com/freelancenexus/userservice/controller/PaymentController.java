package com.freelancenexus.userservice.controller;
 
import com.freelancenexus.userservice.dto.*;
import com.freelancenexus.userservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
 
    private final PaymentService paymentService;
 
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
 
    // INITIATE PAYMENT
    @PostMapping("/initiate")
    public PaymentResponseDTO initiatePayment(@RequestBody PaymentRequestDTO request) {
        return paymentService.initiatePayment(request);
    }
 
    // VERIFY PAYMENT (transactionId-based)
    @PostMapping("/verify/{transactionId}")
    public TransactionStatusDTO verifyPayment(@PathVariable String transactionId) {
        return paymentService.verifyPayment(transactionId);
    }
 
    // GET PAYMENT BY ID
    @GetMapping("/{paymentId}")
    public PaymentResponseDTO getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }
 
    // GET USER PAYMENT HISTORY
    @GetMapping("/user/{userId}")
    public List<PaymentResponseDTO> getUserPayments(@PathVariable Long userId) {
        return paymentService.getUserPaymentHistory(userId);
    }
 
    // GET TRANSACTION HISTORY (Receipt)
    @GetMapping("/{paymentId}/history")
    public List<PaymentHistoryDTO> getPaymentHistory(@PathVariable Long paymentId) {
        return paymentService.getTransactionHistory(paymentId);
    }
 
    // REFUND PAYMENT
    @PostMapping("/{paymentId}/refund")
    public PaymentResponseDTO refundPayment(
            @PathVariable Long paymentId,
            @RequestParam String reason
    ) {
        return paymentService.refundPayment(paymentId, reason);
    }
}