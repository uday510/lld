package com.app.patterns.creational.abstractFactory;

public class AWSFactory implements CloudServiceFactory {

    @Override
    public StorageService createStorageService() {
        return new AWSStorageService();
    }

    @Override
    public QueueService createQueueService() {
        return new AWSQueueService();
    }

}
