package com.app.patterns.behavioral.memento;

import java.util.ArrayDeque;
import java.util.Deque;

// This class manage mementos (snapshots of the text editor state)
public class Caretaker {

    private final Deque<Memento> history = new ArrayDeque<>();

    public void save(Memento memento) {
        history.push(memento);
    }

    public Memento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }

}
