package com.app.patterns.behavioral.memento;

/**
 *
 *
 * A text editor where the user can undo changes, such as text addition, deletion or
 * formatting, the editor stores snapshots of its state (text content)
 * after each change, enabling the user to revert to previous state
 *
 *
 */

public class Editor {

    private String content = "";

    public void write(String text) {
        content += text;
    }

    // save the current state of editor
    public Memento save() {
        return new Memento(content);
    }

    public void restore(Memento memento) {
        this.content = memento.getState();
    }

    public String getContent() {
         return content;
    }
}
