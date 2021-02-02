package com.gameshow.api.Friend;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FriendList {

    private List<Friend> myFriends;
    private List<Friend> pendingSendRequest;
    private List<Friend> pendingReceiveRequest;

}
