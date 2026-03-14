package com.app.patterns.creational.abstractFactory;

public class CloudService {

    private StorageService storage;
    private QueueService queue;

    public CloudService(CloudServiceFactory factory) {
        storage = factory.createStorageService();
        queue = factory.createQueueService();
    }

    public void run() {
        storage.uploadFile("photo.png");
        queue.sendMessage("photo upload event");
    }

}
