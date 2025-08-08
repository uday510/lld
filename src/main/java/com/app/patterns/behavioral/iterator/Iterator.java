package com.app.patterns.behavioral.iterator;

public interface Iterator<T> {

    boolean hasNext();

    T next();
}
