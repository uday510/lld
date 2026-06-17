package com.app.pblms.parking_lot.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Payment {

    private final String paymentId;
    private final PaymentMethod paymentMethod;
    private final BigDecimal amount;
    private final LocalDateTime createdAt;
    private final PaymentStatus status;

    public Payment(String paymentId,
                   PaymentMethod paymentMethod,
                   BigDecimal amount,
                   PaymentStatus status) {

        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.createdAt = LocalDateTime.now();

    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
