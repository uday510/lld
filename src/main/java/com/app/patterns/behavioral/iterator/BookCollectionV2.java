package com.app.patterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class BookCollectionV2 implements Iterable<Book> {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public java.util.Iterator<Book> iterator() {
        return books.iterator();
    }
}
