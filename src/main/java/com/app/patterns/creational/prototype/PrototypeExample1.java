package com.app.patterns.creational.prototype;
import java.util.HashMap;
import java.util.Map;

interface Document extends Cloneable {
    Document clone();
    void print();
}

class ResumeDocument implements Document {
    private String content = "Resume Template Content";

    @Override
    public Document clone() {
        return new ResumeDocument(); // Shallow copy
    }

    @Override
    public void print() {
        System.out.println("Printing Resume Document: " + content);
    }
}

class InvoiceDocument implements Document {
    private String content = "Invoice Template Content";

    @Override
    public Document clone() {
        return new InvoiceDocument();
    }

    @Override
    public void print() {
        System.out.println("Printing Invoice Document: " + content);
    }
}

class DocumentRegistry {
    private Map<String, Document> prototypes = new HashMap<>();

    public DocumentRegistry() {
        prototypes.put("resume", new ResumeDocument());
        prototypes.put("invoice", new InvoiceDocument());
    }

    public Document getDocument(String type) {
        Document prototype = prototypes.get(type);
        if (prototype != null) {
            return prototype.clone();
        }
        return null;
    }
}

public class PrototypeExample1 {

    public static void main(String[] args) {
        DocumentRegistry registry = new DocumentRegistry();

        Document resume1 = registry.getDocument("resume");
        Document resume2 = registry.getDocument("resume");

        Document invoice1 = registry.getDocument("invoice");
        Document invoice2 = registry.getDocument("invoice");

        resume1.print();
        resume2.print();

        invoice1.print();
        invoice2.print();
    }
}
