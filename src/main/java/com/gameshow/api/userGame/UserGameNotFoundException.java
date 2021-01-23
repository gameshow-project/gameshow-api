package com.gameshow.api.userGame;

public class UserGameNotFoundException extends Exception {

    UserGameNotFoundException(UserGameId userGameId) {
        super(userGameId + " not found");
    }

}
