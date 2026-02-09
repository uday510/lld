package com.app.patterns.structural.facade.banking;

public class BankFacade {

    private AccountService account;
    private KYCService kyc;
    private LoanService loan;

    public BankFacade() {
        this.account = new AccountService();
        this.kyc = new KYCService();
        this.loan = new LoanService();
    }

    public void applyForLoan(String user) {
        account.checkAccount(user);
        kyc.verify(user);
        loan.approveLoan(user);

        System.out.println("Loan process completed.");
    }

}
