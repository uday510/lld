package com.app.pblms.splitwise;

import java.util.Objects;

public class User {
    private final String id;
    private final String name;

    public User(String id, String name) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
    }

    public String getId()       { return id; }
    public String getName()     { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof User u)) return false;
        return id.equals(u.id);
    }

    @Override
    public int hashCode()  { return id.hashCode(); }

    @Override
    public String toString()  { return name; }
}
