package com.gameshow.api.userPlatform;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPlatformId implements Serializable {

    private String user;
    private Long platform;

}
