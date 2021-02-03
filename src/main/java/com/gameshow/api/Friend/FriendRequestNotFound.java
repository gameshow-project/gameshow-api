package com.gameshow.api.Friend;

public class FriendRequestNotFound extends Exception {

    FriendRequestNotFound(FriendId friendId) {
        super("Request " + friendId + " not found");
    }

}
