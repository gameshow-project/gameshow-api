package com.gameshow.api.activity;

import com.gameshow.api.shared.Game;
import com.gameshow.api.shared.Platform;
import com.gameshow.api.user.User;
import com.gameshow.api.userGame.UserGame;
import com.gameshow.api.userPlatform.UserPlatform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private Long date;

    @ManyToOne
    private Platform platform;

    @ManyToOne
    private Game game;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany
    private Set<User> users;

}
