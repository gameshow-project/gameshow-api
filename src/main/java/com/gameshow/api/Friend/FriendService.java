package com.gameshow.api.Friend;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public Friend saveRequest(Friend friend) {
        return friendRepository.save(friend);
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

}
