package com.app.pblms.movie_ticket_booking.entities;

import java.util.Objects;

public class User {

    private final String id;
    private final String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId()    { return id; }

    public String getName()  { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User u)) return false;
        return id.equals(u.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
