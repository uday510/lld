package com.app.principles;

/*
High-level modules should not depend on low-level modules. Both should depend on abstractions.
Abstractions should not depend on details. Details should depend on abstractions.

	•	Don’t hardcode dependencies like new EmailService() inside high-level logic.
	•	Use interfaces/abstractions so that both high-level and low-level parts of the code depend on them.
	•	This makes your code flexible, testable, and easy to maintain.
 */

interface MessageService {
    void sendMessage(String message);
}

class EmailService implements MessageService {
    public void sendMessage(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SMSService implements MessageService {
    public void sendMessage(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class NotificationManager {
    private final MessageService messageService;

    // Inject the abstraction (Dependency Injection)
    public NotificationManager(MessageService messageService) {
        this.messageService = messageService;
    }

    public void send(String message) {
        messageService.sendMessage(message);
    }
}

public class DependencyInversion {

    public static void main(String[] args) {
        // High-level module depends on abstraction
        MessageService email = new EmailService();
        NotificationManager emailNotifier = new NotificationManager(email);
        emailNotifier.send("Your OTP is 123456");

        // Low-level module can be swapped easily
        MessageService sms = new SMSService();
        NotificationManager smsNotifier = new NotificationManager(sms);
        smsNotifier.send("Your OTP is 123456.");
    }

}
