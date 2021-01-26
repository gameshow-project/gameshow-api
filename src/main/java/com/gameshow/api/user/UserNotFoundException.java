package com.gameshow.api.user;

public class UserNotFoundException extends Exception {

    UserNotFoundException(String id) {
        super("User with id " + id + " is not found");
    }

}
