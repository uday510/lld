package com.app.patterns.creational.abstractFactory;

public class AWSQueueService implements QueueService {

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending " + message + " to AWS SQS...");
    }

}
