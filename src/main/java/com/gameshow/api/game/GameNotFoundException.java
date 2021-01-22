package com.gameshow.api.game;

public class GameNotFoundException extends Exception {

    public GameNotFoundException(Long id) {
        super("Game " + id + " not found");
    }
}
