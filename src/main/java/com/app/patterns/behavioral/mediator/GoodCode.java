package com.app.patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

interface Mediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}

class User {
    private String name;
    private Mediator mediator;

    public User(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public void sendMessage(String message) {
        System.out.println(this.name + " Sending a Message " + message);
        mediator.sendMessage(message, this);
    }

    public void receiveMessage(String msg, User sender) {
        System.out.println(this.name+ ": New Message From [" + sender.getName() + "] : " + msg);
    }

    public String getName() {
        return name;
    }
}

class ChatRoom implements Mediator {
    private final List<User> users = new ArrayList<>();

    @Override
    public void sendMessage(String msg, User user) {
        for (User currUser : users) {
            if (user == currUser) continue;
            currUser.receiveMessage(msg, user);
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

}

public class GoodCode {

    public static void main(String[] args) {

        ChatRoom chatRoom = new ChatRoom();

        User user1 = new User("user1", chatRoom);
        User user2 = new User("user2", chatRoom);
        User user3 = new User("user3", chatRoom);
        User user4 = new User("user4", chatRoom);
        User user5 = new User("user5", chatRoom);

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);
        chatRoom.addUser(user4);
        chatRoom.addUser(user5);

        user1.sendMessage("hi");

        System.out.println("--------");

        user2.sendMessage("hi");
    }
}
