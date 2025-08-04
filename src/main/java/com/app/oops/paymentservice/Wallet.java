package com.app.oops.paymentservice;

public class Wallet implements PaymentMethod {

    private final String walletId;

    public Wallet(String walletId) {
        this.walletId = walletId;
    }

    public String getWalletId() {
        return walletId;
    }

    public void pay() {
        System.out.println("Making payment using wallet");
    }
}
