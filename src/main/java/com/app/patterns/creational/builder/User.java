package com.app.patterns.creational.builder;

public class User {

    private final String name;
    private final String email;
    private final int age;
    private final String country;
    private final String phone;

    public User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.country = builder.country;
        this.phone = builder.phone;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", age=").append(age);
        sb.append(", country='").append(country).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {

        private String name;
        private String email;
        private int age;
        private String country;
        private String phone;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {

            if (name == null || email == null) {
                throw new IllegalStateException(
                  "Name and Email are required"
                );
            }

            return new User(this);
        }
    }
}
