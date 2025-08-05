package com.app.oops.uml;


class Document {
    private final String content;

    public Document(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class Printer {
    public void print(Document document) {
        System.out.println("Printing ...");
        System.out.println(document.getContent());
    }
}

/**
 *  This is a relationship where one class relies on another in some way,
 *  often way, often parameters, return types or temporary associations.
 *
 */

public class Dependency {

    public static void main(String[] args) {
        Document document = new Document("Dependency Example Document");
        new Printer().print(document);
    }
}
