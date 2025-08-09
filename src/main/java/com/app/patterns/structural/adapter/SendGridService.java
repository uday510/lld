package com.app.patterns.structural.adapter;

public class SendGridService {

    public void sendEmail(String recipient, String title, String content) {
        System.out.println("Sending email via SendGrid :" + recipient);
    }

}
