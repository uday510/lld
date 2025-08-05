package com.app.oops.uml;

import java.util.ArrayList;
import java.util.List;

class House {
    private List<Room> rooms;

    public House() {
        this.rooms = new ArrayList<>();
        rooms.add(new Room("room1"));
        rooms.add(new Room("room2"));
    }

    public List<Room> getRooms() {
        return rooms;
    }

}

class Room {
    String roomId;

    public Room(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }
}


/**
 *  Composition is a strong "has-a" relationship, where one class owns objects of another class.
 *  If the container object is destroyed, the container objects are destroyed as well.
 *
 */
public class Composition {

    public static void main(String[] args) {

        House house = new House();

        System.out.println(house.getRooms());
    }

}
