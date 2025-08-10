package com.app.patterns.structural.facade;

public class UserService {

    public String getUserDetails(String userId) {
        return "User Details [ userId: " + userId + " ]";
    }

}
