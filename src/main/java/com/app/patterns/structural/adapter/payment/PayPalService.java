package com.app.patterns.structural.adapter.payment;

public class PayPalService {

    public void makePayment(double money) {
        System.out.println("Paid via PayPal: " + money);
    }

}
