package com.app.principles;


/**
 * Clients should not be forced to depend on interfaces they do not use.
 */

/**
 *  Don’t create fat interfaces.
 */

class Document {}

interface PrinterInterface {
    void print(Document document);
}

interface Scanner {
    void scan(Document document);
}

interface Fax {
    void fax(Document document);
}


class JustAPrinter implements PrinterInterface {
    public void print(Document document) {
        System.out.println("Printing document...");
    }
}

class Photocopier implements PrinterInterface, Scanner {
    public void print(Document document) {
        System.out.println("Photocopier printing...");
    }

    public void scan(Document document) {
        System.out.println("Photocopier scanning...");
    }
}

class MultiFunctionMachine implements PrinterInterface, Scanner, Fax {
    public void print(Document document) {
        System.out.println("MultiFunctionMachine printing...");
    }
    public void scan(Document document) {
        System.out.println("MultiFunctionMachine scanning...");
    }
    public void fax(Document document) {
        System.out.println("MultiFunctionMachine faxing...");
    }
}


public class InterfaceSegregationPrinciple {

    public static void main(String[] args) {
        Document document = new Document();

        PrinterInterface printer = new JustAPrinter();
        printer.print(document);

        Scanner scanner = new Photocopier();
        scanner.scan(document);

        MultiFunctionMachine mfm = new MultiFunctionMachine();
        mfm.print(document);
        mfm.scan(document);
        mfm.fax(document);

    }
}
