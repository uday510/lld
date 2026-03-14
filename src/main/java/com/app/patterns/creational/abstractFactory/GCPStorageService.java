package com.app.patterns.creational.abstractFactory;

public class GCPStorageService implements StorageService {

    @Override
    public void uploadFile(String fileName) {
        System.out.println(fileName + " Uploading to GCP Storage...");
    }
}
