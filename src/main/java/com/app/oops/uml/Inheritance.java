package com.app.oops.uml;

class Animal {
    private final String name;

    Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}



/**
 *
 * Inheritance defines "is-a" relationship where a subclass inherits
 * properties and behaviors(methods) from a superclass.
 */
public class Inheritance {

}
