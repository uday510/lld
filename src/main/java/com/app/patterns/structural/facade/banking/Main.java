package com.app.patterns.structural.facade.banking;

public class Main {

    static void main() {

        BankFacade bank = new BankFacade();

        bank.applyForLoan("User");
    }

}
