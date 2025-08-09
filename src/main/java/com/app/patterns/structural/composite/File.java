package com.app.patterns.structural.composite;

public class File implements FileSystemComponent {
    String name;

    public File(String name) {
        this.name = name;
    }


    @Override
    public void ls() {
        ls(0);
    }

    public void ls(int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "\u001B[32mFile: " + name + "\u001B[0m");
    }
}
