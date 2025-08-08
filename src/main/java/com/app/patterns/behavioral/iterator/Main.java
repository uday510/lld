package com.app.patterns.behavioral.iterator;

public class Main {

    public static void main(String[] args) {

        BookCollection bookCollection = new BookCollection();
        bookCollection.addBook(new Book("Book1"));
        bookCollection.addBook(new Book("Book2"));
        bookCollection.addBook(new Book("Book3"));
        bookCollection.addBook(new Book("Book4"));

        Iterator<Book> iterator = bookCollection.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }

    }
}
