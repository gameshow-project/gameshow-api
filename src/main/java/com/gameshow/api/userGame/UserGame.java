package com.gameshow.api.userGame;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gameshow.api.shared.Game;
import com.gameshow.api.shared.Platform;
import com.gameshow.api.user.User;
import com.gameshow.api.userPlatform.UserPlatformId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user_game")
@IdClass(UserGameId.class)
public class UserGame {

    @Id
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private int nbFinished;

    private boolean platinum;

}
