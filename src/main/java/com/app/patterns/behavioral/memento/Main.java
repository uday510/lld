package com.app.patterns.behavioral.memento;

public class Main {

    public static void main(String[] args) {

        Editor editor = new Editor();
        Caretaker caretaker = new Caretaker();

        editor.write("Hello, World! ");
        caretaker.save(editor);

        editor.write("Alan Turing");
        caretaker.save(editor);

        caretaker.undo(editor);
        System.out.println(editor.getContent());
    }
}
