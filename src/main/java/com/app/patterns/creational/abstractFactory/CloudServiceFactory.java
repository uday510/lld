package com.app.patterns.creational.abstractFactory;

public interface CloudServiceFactory {

    StorageService createStorageService();
    QueueService createQueueService();

}
