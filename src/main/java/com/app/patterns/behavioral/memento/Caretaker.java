package com.app.patterns.behavioral.memento;

import java.util.ArrayDeque;
import java.util.Deque;

// This class manage mementos (snapshots of the text editor state)
public class Caretaker {

    private final Deque<Memento> history = new ArrayDeque<>();

    public void save(Editor editor) {
        history.push(editor.save());
    }

    public void undo(Editor editor) {
        if (!history.isEmpty()) {
            history.pop();
            assert history.peek() != null;
            editor.restore(history.peek());
        }
    }

}
