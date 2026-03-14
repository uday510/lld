package com.app.patterns.creational.abstractFactory;

public class GCPQueueService implements QueueService {

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending " + message + " to GCP Queue...");
    }
}
