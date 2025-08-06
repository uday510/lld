package com.app.principles;

/*
Dependency Injection is a design pattern in which an object receives its dependencies from an external source rather than creating them itself.
 */

class NotificationService {
    private final MessageService messageService;

    // Constructor injection
    public NotificationService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String msg) {
        messageService.sendMessage(msg);
    }
}

public class DependencyInjection {
    public static void main(String[] args) {
        MessageService email = new EmailService(); // low-level module
        NotificationService service = new NotificationService(email); // injected here

        service.notifyUser("Hello! Welcome to SOLID principles.");
    }
}