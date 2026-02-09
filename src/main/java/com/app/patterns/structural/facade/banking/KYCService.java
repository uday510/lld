package com.app.patterns.structural.facade.banking;

public class KYCService {

    public void verify(String user) {
        System.out.println("KYC Verification success for " + user);
    }

}
