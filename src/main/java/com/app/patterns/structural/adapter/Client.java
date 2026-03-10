package com.app.patterns.structural.adapter;

public class Client {

    static void main(String[] args) {
        NotificationService emailService = new EmailNotificationService();
        emailService.send("customer@example.com", "order placed", "Thank you for your order");

        emailService = new SendGridAdapter(new SendGridService());

        emailService.send("customer@example.com", "order placed", "Thank you for your order");
    }
}
