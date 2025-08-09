package com.app.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {

    private String name;
    private final List<FileSystemComponent> components;

    public Folder(String name) {
        this.components = new ArrayList<>();
        this.name = name;
    }

    public void add(FileSystemComponent fileSystemComponent) {
        components.add(fileSystemComponent);
    }

    public void ls() {
        ls(0);
    }

    private void ls(int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "\u001B[34m" + name + "\u001B[0m");
        for (FileSystemComponent component : components) {
            if (component instanceof Folder) {
                ((Folder) component).ls(depth + 1);
            } else if (component instanceof File) {
                ((File) component).ls(depth + 1);
            }
        }
    }

}
