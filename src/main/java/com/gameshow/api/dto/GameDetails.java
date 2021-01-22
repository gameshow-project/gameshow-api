package com.gameshow.api.dto;

import com.gameshow.api.shared.Game;
import com.gameshow.api.userGame.UserGame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDetails {

    private Game game;
    private UserGame userGame;
    private int nbFollowed;

}
