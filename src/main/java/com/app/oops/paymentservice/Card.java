package com.app.oops.paymentservice;

public class Card {

    private final String userName;
    private final String cardNumber;

    public Card(String userName, String cardNumber) {
        this.userName = userName;
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getUserName() {
        return userName;
    }
}
