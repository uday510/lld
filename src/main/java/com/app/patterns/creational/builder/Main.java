package com.app.patterns.creational.builder;

public class Main {

    static void main() {

        User user = User.builder()
                .name("user")
                .email("user@email.com")
                .age(16)
                .phone("+123 456 789 012")
                .country("Country")
                .build();

        System.out.println("New User Created : " + user);
    }
}
