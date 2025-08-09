package com.app.patterns.structural.composite;

public class Main {

    public static void main(String[] args) {

        File file1 = new File("document1.txt");
        File file2 = new File("images1.png");
        Folder folder = new Folder("documents");

        Folder subFolder = new Folder("images");

        folder.add(file1);
        folder.add(subFolder);
        folder.add(file2);

        folder.ls();
    }
}
