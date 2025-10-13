package com.app.pblms.parkinglot.payment;

import java.math.BigDecimal;

/**
 * PaymentProcessor defines a contact for all payment methods.
 * Allows adding new payment types easily (cash, card, online)
 */
public interface PaymentProcessor {

    /**
     * Processes a payment for the given amount.
     * @param amount amount to process
     * @return Payment object representing the transaction
     */
    Payment process(BigDecimal amount);
}
