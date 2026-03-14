//package com.app.patterns.creational.abstractFactory;
//
//interface StorageService {
//    void uploadFile(String file);
//}
//
//interface QueueService {
//    void sendMessage(String msg);
//}
//
//class AWSStorageService implements StorageService {
//
//    public void uploadFile(String file) {
//        System.out.println("Uploading file to AWS S3: " + file);
//    }
//
//}
//
//class AwsQueueService implements QueueService {
//
//    public void sendMessage(String msg) {
//        System.out.println("Sending message to AWS SQS: " + msg);
//    }
//
//}
//class GCPStorageService implements StorageService {
//
//    public void uploadFile(String file) {
//        System.out.println("Uploading file to GCP Storage: " + file);
//    }
//
//}
//
//class GCPQueueService implements QueueService {
//
//    public void sendMessage(String msg) {
//        System.out.println("Sending message to GCP Queue: " + msg);
//    }
//
//}
//
//
//interface CloudServiceFactory {
//
//    StorageService createStorageService();
//
//    QueueService createQueueService();
//}
//
//class AWSFactory implements CloudServiceFactory {
//
//    public StorageService createStorageService() {
//        return new AWSStorageService();
//    }
//
//    public QueueService createQueueService() {
//        return new AwsQueueService();
//    }
//
//}
//
//class GCPFactory implements CloudServiceFactory {
//
//    public StorageService createStorageService() {
//        return new GCPStorageService();
//    }
//
//    public QueueService createQueueService() {
//        return new GCPQueueService();
//    }
//}
//
//class CloudApplication {
//
//    private StorageService storage;
//    private QueueService queue;
//
//    public CloudApplication(CloudServiceFactory cloudServiceFactory) {
//        this.storage = cloudServiceFactory.createStorageService();
//        this.queue = cloudServiceFactory.createQueueService();
//    }
//
//    public void run() {
//
//        storage.uploadFile("photo.png");
//        queue.sendMessage("File upload event");
//    }
//}
//
//public class Solution {
//
//    static void main() {
//
//        CloudServiceFactory factory = new GCPFactory();
//
//        CloudApplication app = new CloudApplication(factory);
//
//        app.run();
//    }
//}