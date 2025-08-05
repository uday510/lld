package com.app.oops.paymentservice;

public class Client {

    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();

        paymentService.addPaymentMethod(PaymentType.CREDIT_CARD, new DebitCard("USER 1", "1234-5678-9012-1234"));
        paymentService.addPaymentMethod(PaymentType.DEBIT_CARD, new CreditCard("USER 2", "2234-5678-9012-1234"));
        paymentService.addPaymentMethod(PaymentType.UPI, new UPI("user@upi"));
        paymentService.addPaymentMethod(PaymentType.WALLET, new Wallet("wallet12345"));

        paymentService.makePayment(PaymentType.CREDIT_CARD);
        paymentService.makePayment(PaymentType.DEBIT_CARD);
        paymentService.makePayment(PaymentType.UPI);
        paymentService.makePayment(PaymentType.WALLET);

    }
}
