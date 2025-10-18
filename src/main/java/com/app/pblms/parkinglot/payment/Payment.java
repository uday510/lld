package com.app.pblms.parkinglot.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a payment transaction for a parking session.
 */
public class Payment {

    private final String paymentId;
    private final PaymentMethod paymentMethod;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;
    private final PaymentStatus status;

    public Payment(BigDecimal amount, PaymentMethod paymentMethod, PaymentStatus status, LocalDateTime timestamp) {
        this.paymentId = generatePaymentId();
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.timestamp = timestamp;
    }

    private String generatePaymentId() {
        return STR."PAY-\{System.currentTimeMillis() + ((int) (Math.random() * 1000)) }";
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return STR."Payment{paymentId='\{paymentId}', amount=\{amount}, method='\{paymentMethod}', timestamp=\{timestamp}, status=\{status}}";
    }
}
