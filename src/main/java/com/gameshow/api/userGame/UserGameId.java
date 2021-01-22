package com.gameshow.api.userGame;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserGameId implements Serializable {

    private Long user;
    private Long game;

}
