package com.app.patterns.structural.facade.banking;

public class LoanService {

    public void approveLoan(String user) {
        System.out.println("Loan approved for " + user);
    }

}
