package com.app.oops.paymentservice;

public class CreditCard extends Card implements PaymentMethod {

    public CreditCard(String userName, String cardNumber) {
        super(userName, cardNumber);
    }

    public void pay() {
        System.out.println("Making payment using credit card");
    }

}
