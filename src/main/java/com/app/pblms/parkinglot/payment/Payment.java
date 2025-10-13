package com.app.pblms.parkinglot.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a payment transaction for a parking session.
 */
public class Payment {

    private final String paymentId;
    private final BigDecimal amount;
    private final PaymentMethod method;
    private final LocalDateTime timestamp;
    private final PaymentStatus status;

    public Payment(BigDecimal amount, PaymentMethod method, LocalDateTime timestamp, PaymentStatus status) {
        this.paymentId = generatePaymentId();
        this.amount = amount;
        this.method = method;
        this.timestamp = timestamp;
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return STR."Payment{paymentId='\{paymentId}', amount=\{amount}, method='\{method}', timestamp=\{timestamp}, status=\{status}}";
    }

    private String generatePaymentId() {
        return STR."PAY-\{System.currentTimeMillis()}-\{(int) (Math.random() * 1000)}";
    }
}
