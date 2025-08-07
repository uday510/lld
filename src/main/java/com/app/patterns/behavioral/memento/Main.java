package com.app.patterns.behavioral.memento;

public class Main {

    public static void main(String[] args) {

        Editor editor = new Editor();
        Caretaker caretaker = new Caretaker();

        editor.write("Hello, ");
        caretaker.save(editor.save());

        System.out.println(editor.getContent());
        editor.write("World!");
        caretaker.save(editor.save());

        System.out.println(editor.getContent());

        editor.restore(caretaker.undo());

        System.out.println(editor.getContent());
    }
}
