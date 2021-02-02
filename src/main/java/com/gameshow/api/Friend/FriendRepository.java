package com.gameshow.api.Friend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {

    List<Friend> findAllBySenderUidAndFriendStatus(String uid, FriendStatus friendStatus);

    List<Friend> findAllByReceiverUidAndFriendStatus(String uid, FriendStatus friendStatus);
}
