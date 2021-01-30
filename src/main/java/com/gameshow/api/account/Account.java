package com.gameshow.api.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gameshow.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @OneToOne
    private User user;

    @NotNull
    @Size(min = 2, max = 100)
    private String accountYoutube;

    @NotNull
    @Size(min = 2, max = 100)
    private String accountTwitch;

    @NotNull
    @Size(min = 2, max = 100)
    private String accountDiscord;

    @NotNull
    @Size(min = 2, max = 100)
    private String accountPsn;

    @NotNull
    @Size(min = 2, max = 100)
    private String accountXbox;

    @NotNull
    @Size(min = 2, max = 100)
    private String accountOrigin;

    @NotNull
    @Size(min = 2, max = 100)
    private String accountUbisoft;

    @NotNull
    @Size(min = 2, max = 100)
    private String accountEpicGames;

    @NotNull
    @Size(min = 2, max = 100)
    private String accountSteam;

    @NotNull
    @Size(min = 2, max = 100)
    private String accountNintendo;

}
