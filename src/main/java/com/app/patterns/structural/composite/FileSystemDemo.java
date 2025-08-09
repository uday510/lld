//package com.app.patterns.structural.composite;
//
//// Compose objects into tree structures to represent part-whole hierarchies. Clients can treat individual objects and compositions uniformly.
//import java.util.ArrayList;
//import java.util.List;
//
//abstract class FileSystemComponent {
//    String name;
//    public FileSystemComponent(String name) {
//        this.name = name;
//    }
//    public void add(FileSystemComponent component) {
//        throw new UnsupportedOperationException();
//    }
//    public void remove(FileSystemComponent component) {
//        throw new UnsupportedOperationException();
//    }
//    public abstract void show();
//}
//
//class File extends FileSystemComponent {
//    public File(String name) {
//        super(name);
//    }
//    @Override
//    public void show() {
//        System.out.println("File: " + name);
//    }
//}
//
//class Directory extends FileSystemComponent {
//    List<FileSystemComponent> children = new ArrayList<>();
//
//    public Directory(String name) {
//        super(name);
//    }
//
//    @Override
//    public void add(FileSystemComponent component) {
//        children.add(component);
//    }
//
//    @Override
//    public void show() {
//        System.out.println("Directory: " + name);
//        for (FileSystemComponent component : children) {
//            component.show();
//        }
//    }
//}
//
//public class FileSystemDemo {
//
//    public static void main(String[] args) {
//        Directory root = new Directory("/");
//        File file1 = new File("file1.txt");
//        File file2 = new File("file2.txt");
//
//        Directory subDir = new Directory("usr");
//        File file3 = new File("file3.txt");
//
//        root.add(file1);
//        root.add(file2);
//        subDir.add(file3);
//        root.add(subDir);
//
//        root.show();
//    }
//
//
//}
