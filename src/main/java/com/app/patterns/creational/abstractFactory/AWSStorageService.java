package com.app.patterns.creational.abstractFactory;

public class AWSStorageService implements StorageService {

    @Override
    public void uploadFile(String fileName) {
        System.out.println(fileName + " uploading to AWS Storage..." );
    }
}
