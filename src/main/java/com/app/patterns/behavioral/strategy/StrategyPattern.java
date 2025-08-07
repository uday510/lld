package com.app.patterns.behavioral.strategy;

interface PaymentStrategy {
    void processPayment();
}

class UPIPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("UPI Payment Processing...");
    }
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("CreditCard Payment Processing...");
    }
}
class DebitCardPayment implements PaymentStrategy {
    @Override
    public void processPayment() {
        System.out.println("DebitCard Payment Processing...");
    }
}


class PaymentService {

    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay() {
        paymentStrategy.processPayment();
    }

}

public class StrategyPattern {

    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();
        paymentService.setPaymentStrategy(new CreditCardPayment());

        paymentService.pay();
    }

}
