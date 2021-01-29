package com.gameshow.api.user;

import com.gameshow.api.account.Account;
import com.gameshow.api.userGame.UserGame;
import com.gameshow.api.userPlatform.UserPlatform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    private String uid;

    private String name;

    private String email;

    private boolean isEmailVerified;

    private String issuer;

    private String picture;

    @NotNull
    @Size(min = 2, max = 50)
    private String firstname;

    private String banner;

    @NotNull
    @Size(min = 2, max = 50)
    private String lastname;

    @NotNull
    @Size(min = 2, max = 50)
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "user")
    private List<UserGame> userGames;

    @OneToMany(mappedBy = "user")
    private List<UserPlatform> userPlatforms;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;
}
