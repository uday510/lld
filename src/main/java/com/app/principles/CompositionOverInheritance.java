package com.app.principles;

interface FlyBehavior {
    void fly();
}

interface QuackBehavior {
    void quack();
}

class FlyWithWings implements FlyBehavior {
    public void fly() {
        System.out.println("I'm flying with wings");
    }
}

class FlyNoWings implements FlyBehavior {
    public void fly() {
        System.out.println("I can't fly");
    }
}

class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("Quack!");
    }
}

abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public abstract void display();
}

class MallardDuck extends Duck {
    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }
    public void display() {
        System.out.println("I'm a real Mallard Duck");
    }
}

class RubberDuck extends Duck {
    public RubberDuck() {
        flyBehavior = new FlyNoWings();
        quackBehavior = new Quack();
    }

    public void display() {
        System.out.println("I'm a real Rubber Duck");
    }
}

public class CompositionOverInheritance {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.display();
        mallard.performFly();
        mallard.performQuack();

        System.out.println("----");

        Duck rubber = new RubberDuck();
        rubber.display();
        rubber.performFly();
        rubber.performQuack();

        // Changing behavior at runtime
        rubber.setFlyBehavior(new FlyWithWings());
        rubber.performFly(); // Now it can fly!
    }
}
