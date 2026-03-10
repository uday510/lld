package com.app.patterns.structural.adapter.payment;

public class Application {

    static void main() {

        PaymentGateway paypal = new PayPalGateway();
        PaymentService paypalService = new PaymentService(paypal);

        paypalService.makePayment(250.50);

        StripeClient stripeClient = new StripeClient();

        PaymentGateway stripeAdapter = new StripePaymentAdapter(stripeClient);

        PaymentService striveService = new PaymentService(stripeAdapter);

        striveService.makePayment(120.75);

    }
}
