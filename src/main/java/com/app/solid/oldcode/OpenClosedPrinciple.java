package com.app.solid.oldcode;

interface PaymentMethod {
    void pay(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class UpiPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Upi");
    }
}

class DebitCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Debit Card");
    }
}

class PaymentProcessor {
    private final PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void processPayment(double amount) {
        paymentMethod.pay(amount);
    }
}

public class OpenClosedPrinciple {

    public static void main(String[] args) {

        PaymentProcessor creditCardPaymentProcessor = new PaymentProcessor(new CreditCardPayment());
        creditCardPaymentProcessor.processPayment(2500);

        PaymentProcessor upiPaymentProcessor = new PaymentProcessor(new UpiPayment());
        upiPaymentProcessor.processPayment(1100);

        PaymentProcessor debitCardPaymentProcessor = new PaymentProcessor(new DebitCardPayment());
        debitCardPaymentProcessor.processPayment(1000);

    }

}
