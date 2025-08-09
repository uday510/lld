package com.app.patterns.structural.adapter;

public class SendGridAdapter implements NotificationService {

    private final SendGridService sendGridService;

    public SendGridAdapter(SendGridService sendGridService) {
        this.sendGridService = sendGridService;
    }

    @Override
    public void send(String to, String subject, String body) {
        // Adapter Method --> convert parameter and calls to SendGrid Method
        sendGridService.sendEmail(to, subject, body);
    }
}
