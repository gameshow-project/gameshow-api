package com.gameshow.api.Friend;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public Friend saveRequest(Friend friend) {
        return friendRepository.save(friend);
    }

    public Friend getFriend(FriendId friendId) throws FriendRequestNotFound {
        return friendRepository.findById(friendId).orElseThrow(() -> new FriendRequestNotFound(friendId));
    }

    public boolean alreadyRequest(String idOne, String idTwo) {
        FriendId oneFriendId = new FriendId();
        oneFriendId.setSender(idOne);
        oneFriendId.setReceiver(idTwo);
        FriendId twoFriendId = new FriendId();
        twoFriendId.setSender(idTwo);
        twoFriendId.setReceiver(idOne);
        return friendRepository.existsById(oneFriendId) || friendRepository.existsById(twoFriendId);
    }

    public Friend getRequestOrNull(String idOne, String idTwo) {
        FriendId oneFriendId = new FriendId();
        oneFriendId.setSender(idOne);
        oneFriendId.setReceiver(idTwo);
        if (friendRepository.existsById(oneFriendId)) {
            return friendRepository.getOne(oneFriendId);
        }
        oneFriendId.setSender(idTwo);
        oneFriendId.setReceiver(idOne);
        if (friendRepository.existsById(oneFriendId)) {
            return friendRepository.getOne(oneFriendId);
        }
        return null;
    }

    public FriendList getFriendList(String userId) {
        List<Friend> senderAccept = friendRepository.findAllBySenderUidAndFriendStatus(userId, FriendStatus.ACCEPT);
        List<Friend> receiverAccept = friendRepository.findAllByReceiverUidAndFriendStatus(userId, FriendStatus.ACCEPT);
        senderAccept.addAll(receiverAccept);
        List<Friend> pendingSender = friendRepository.findAllBySenderUidAndFriendStatus(userId, FriendStatus.PENDING);
        List<Friend> pendingReceiver = friendRepository.findAllByReceiverUidAndFriendStatus(userId, FriendStatus.PENDING);
        return FriendList.builder()
                .myFriends(senderAccept)
                .pendingReceiveRequest(pendingReceiver)
                .pendingSendRequest(pendingSender).build();
    }

    public void deleteFriend(Friend friend) {
        this.friendRepository.delete(friend);
    }

}
