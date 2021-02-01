package com.gameshow.api.Friend;

import com.gameshow.api.user.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class FriendId implements Serializable {

    private String sender;
    private String receiver;

}
