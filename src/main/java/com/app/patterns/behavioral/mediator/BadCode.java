//package com.app.patterns.behavioral.mediator;
//
//
//class User {
//    private final String name;
//
//    public User(String name) {
//        this.name = name;
//    }
//
//    public void sendMessage(String message, User recipient) {
//        System.out.println(this.name + " sending" + message + " to " + recipient.getName() + "...");
//    }
//
//    public String getName() {
//        return name;
//    }
//
//}
//public class BadCode {
//
//    public static void main(String[] args) {
//
//        User user1 = new User("user1");
//        User user2 = new User("user2");
//        User user3 = new User("user3");
//
//        user1.sendMessage("", user2);
//        user2.sendMessage("", user3);
//
//    }
//}
