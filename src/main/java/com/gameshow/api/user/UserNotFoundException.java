package com.gameshow.api.user;

public class UserNotFoundException extends Exception {

    UserNotFoundException(Long id) {
        super("User with id " + id + " is not found");
    }

}
