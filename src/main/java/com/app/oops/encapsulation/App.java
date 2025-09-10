package com.app.oops.encapsulation;

public class App {

    static void main() {

        User user = new User("alan.turing@email.com", "alan-turing");

        System.out.println(user);

        user.setUsername("alan");

        System.out.println(user);
    }

}
