package com.app.oops.encapsulation;

public class User {
    // Private data members (attributes)
    private String email;
    private String username;

    // Public constructor
    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    // Public getter methods(
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username +
                '}';
    }
}
