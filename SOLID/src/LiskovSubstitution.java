class Bird {
    public void fly() {
        System.out.println("Bird is flying.");
    }
}

class Ostrich extends Bird {
    // An ostrich cannot fly, but it respects the LSP by not changing the method's contract.
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow is flying.");
    }
}

public class LiskovSubstitutionPrinciple {

    //TODO:  The LSP states that objects of a derived class should be able to replace objects of the base class without affecting the correctness of the program.
    //TODO:  "Subtypes must be substitutable for their base types without altering the correctness of the program."
    public static void main(String[] args) {
        Bird bird1 = new Sparrow();
        Bird bird2 = new Ostrich();

        bird1.fly();  // Output: Sparrow is flying.
        bird2.fly();  // Output: Bird is flying.
    }
}
