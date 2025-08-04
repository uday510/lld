package com.app.oops.paymentservice;

public class UPI implements PaymentMethod {

    private final String upiId;

    public UPI(String upiId) {
        this.upiId = upiId;
    }

    public void pay() {
        System.out.println("Making payment using UPI");
    }

    public String getUpiId() {
        return upiId;
    }
}
