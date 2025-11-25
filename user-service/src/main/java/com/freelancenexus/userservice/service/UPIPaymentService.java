package com.freelancenexus.userservice.service;
 
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
 
import com.freelancenexus.userservice.dto.UPIPaymentDTO;
 
@Service
public class UPIPaymentService {
 
    private final Map<String, UPIPaymentDTO> upiTransactions = new ConcurrentHashMap<>();
    private final Logger log = LoggerFactory.getLogger(UPIPaymentService.class);
    private final SecureRandom random = new SecureRandom();
 
    /**
     * Generate UPI payment link (Simulated)
     */
    public UPIPaymentDTO generateUPIPaymentLink(String transactionId,
                                                BigDecimal amount,
                                                String upiId,
                                                String currency) {
        log.info("Generating simple UPI payment link for {}", transactionId);
 
        String paymentLink = String.format(
                "upi://pay?pa=%s&pn=FreelanceNexus&am=%s&cu=%s&tn=%s",
                upiId,
                amount.toString(),
                currency,
                transactionId
        );
 
        String qrCode = generateFakeQRCode();
 
        UPIPaymentDTO dto = new UPIPaymentDTO(
                transactionId,
                upiId,
                amount,
                currency,
                paymentLink,
                qrCode,
                900L,
                "INITIATED",
                "UPI link generated"
        );
 
        upiTransactions.put(transactionId, dto);
        return dto;
    }
 
    /**
     * SIMPLE verification: Always return success
     * You wanted simple UPI flow → this ensures instant success
     */
    public TransactionVerificationResult verifyUPIPayment(String transactionId) {
        log.info("Simulating UPI verification for {}", transactionId);
 
        UPIPaymentDTO txn = upiTransactions.get(transactionId);
        if (txn == null) {
            return new TransactionVerificationResult(false, "FAILED", "Transaction not found");
        }
 
        txn.setStatus("SUCCESS");
        txn.setMessage("UPI Payment completed successfully");
 
        return new TransactionVerificationResult(true, "SUCCESS", "Payment verified successfully");
    }
 
    /**
     * Simple Refund Simulation (Optional)
     */
    public RefundResult initiateRefund(String transactionId, BigDecimal amount) {
        log.info("Simulating UPI refund for {}", transactionId);
 
        UPIPaymentDTO txn = upiTransactions.get(transactionId);
        if (txn == null) {
            return new RefundResult(false, null, "Transaction not found");
        }
 
        if (!"SUCCESS".equals(txn.getStatus())) {
            return new RefundResult(false, null, "Only successful payments can be refunded");
        }
 
        String refundTxnId = "RFD-" + UUID.randomUUID();
        return new RefundResult(true, refundTxnId, "Refund processed successfully");
    }
 
    /**
     * Fake QR generator
     */
    private String generateFakeQRCode() {
        byte[] qrBytes = new byte[128];
        random.nextBytes(qrBytes);
        return Base64.getEncoder().encodeToString(qrBytes);
    }
 
    /**
     * Validate UPI format
     */
    public boolean isValidUPIId(String upiId) {
        return upiId != null && upiId.matches("^[\\w.-]+@[\\w.-]+$");
    }
 
    // Inner result classes
    public static class TransactionVerificationResult {
        private final boolean success;
        private final String status;
        private final String message;
 
        public TransactionVerificationResult(boolean success, String status, String message) {
            this.success = success;
            this.status = status;
            this.message = message;
        }
        public boolean isSuccess() { return success; }
        public String getStatus() { return status; }
        public String getMessage() { return message; }
    }
 
    public static class RefundResult {
        private final boolean success;
        private final String refundTransactionId;
        private final String message;
 
        public RefundResult(boolean success, String refundTransactionId, String message) {
            this.success = success;
            this.refundTransactionId = refundTransactionId;
            this.message = message;
        }
        public boolean isSuccess() { return success; }
        public String getRefundTransactionId() { return refundTransactionId; }
        public String getMessage() { return message; }
    }
}