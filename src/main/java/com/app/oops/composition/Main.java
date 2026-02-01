package com.app.oops.composition;

public class Main {

    static void main() {

        Dog dog = new Dog(new DogBark());
        RobotDog robotDog = new RobotDog(new RobotBark());

        dog.bark();
        robotDog.bark();
    }

}
