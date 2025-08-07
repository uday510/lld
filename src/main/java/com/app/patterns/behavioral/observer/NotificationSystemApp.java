package com.app.patterns.behavioral.observer;

import com.app.patterns.behavioral.iterator.SocialMediaApp;

import java.util.ArrayList;
import java.util.List;

interface Notifier {
    void update(String message);
}

interface NotificationService {
    void register(Notifier notifier);
    void unregister(Notifier notifier);
    void notifyAll(String message);
}

class UserNotificationService implements NotificationService {
    private List<Notifier> notifiers = new ArrayList<>();

    @Override
    public void register(Notifier notifier) {
        notifiers.add(notifier);
    }

    @Override
    public void unregister(Notifier notifier) {
        notifiers.remove(notifier);
    }

    @Override
    public void notifyAll(String message) {
        for (Notifier notifier : notifiers) {
            notifier.update(message);
        }
    }

    public void triggerEvent(String eventMessage) {
        System.out.println("System: " + eventMessage);
        notifyAll(eventMessage);
    }
}

class EmailNotifier implements Notifier {
    @Override
    public void update(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SMSNotifier implements Notifier {
    @Override
    public void update(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class AppNotifier implements Notifier {
    @Override
    public void update(String message) {
        System.out.println("Push notification: " + message);
    }
}

public class NotificationSystemApp {

    public static void main(String[] args) {
        UserNotificationService notificationService = new UserNotificationService();

        Notifier email = new EmailNotifier();
        Notifier sms = new SMSNotifier();
        Notifier app = new AppNotifier();

        notificationService.register(email);
        notificationService.register(sms);
        notificationService.register(app);

        notificationService.triggerEvent("Transaction of ₹5,000 completed.");
        System.out.println("---------------------------------");
        notificationService.triggerEvent("New login from Chrome browser.");
    }

}
