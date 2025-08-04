package com.app.oops.paymentservice;

public class DebitCard extends Card implements PaymentMethod {

    public DebitCard(String userName, String cardNumber) {
        super(userName, cardNumber);
    }

    public void pay() {
        System.out.println("Making payment using debit card");
    }

}
