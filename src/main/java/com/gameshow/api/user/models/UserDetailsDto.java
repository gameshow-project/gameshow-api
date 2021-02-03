package com.gameshow.api.user.models;

import com.gameshow.api.Friend.Friend;
import com.gameshow.api.account.Account;
import com.gameshow.api.user.Visibility;
import com.gameshow.api.userGame.UserGame;
import com.gameshow.api.userPlatform.UserPlatform;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class UserDetailsDto {

    private String uid;
    private String name;
    private String email;
    private boolean isEmailVerified;
    private String issuer;
    private String picture;
    private String firstname;
    private String banner;
    private String lastname;
    private String username;
    private Account account;
    private String bio;
    private List<UserGame> userGames;
    private List<UserPlatform> userPlatforms;
    private Visibility visibility;
    private boolean myProfil;
    private Friend friend;

}
