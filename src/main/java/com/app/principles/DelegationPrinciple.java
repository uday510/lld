package com.app.principles;

// The delegate class
class Printer {
    public void print(String message) {
        System.out.println("Printing: " + message);
    }
}

// The delegator class
class Manager {
    private final Printer printer = new Printer(); // Composition

    public void printDocument(String message) {
        // Delegating the printing task to Printer
        printer.print(message);
    }
}

public class DelegationPrinciple {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.printDocument("Hello from the Delegation Principle!");
    }
}