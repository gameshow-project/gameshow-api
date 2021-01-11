package com.gameshow.api.auth;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class Signin {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
