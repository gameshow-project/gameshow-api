package com.gameshow.api.Friend;

import com.gameshow.api.security.SecurityService;
import com.gameshow.api.user.User;
import com.gameshow.api.user.UserNotFoundException;
import com.gameshow.api.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/friend")
public class FriendController {

    private final FriendService friendService;
    private final SecurityService securityService;
    private final UserService userService;

    @PostMapping("/request/{receiverId}")
    public ResponseEntity<Friend> createRequest(@PathVariable("receiverId") String receiverId) throws UserNotFoundException {
        User userSecurity = securityService.getUser();
        User sender = userService.findById(userSecurity.getUid());
        User receiver = userService.findById(receiverId);
        if (friendService.alreadyRequest(sender.getUid(), receiverId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Friend friend = Friend.builder()
                .friendStatus(FriendStatus.PENDING)
                .sender(sender)
                .receiver(receiver).build();
        return ResponseEntity.ok(friendService.saveRequest(friend));
    }

    @GetMapping("/friend_list")
    public ResponseEntity<FriendList> getFriendList() {
        User userSecurity = securityService.getUser();
        return ResponseEntity.ok(friendService.getFriendList(userSecurity.getUid()));
    }

    @PostMapping("/accept")
    public ResponseEntity<Friend> acceptRequest(@RequestBody Friend friend) {
        User userSecurity = securityService.getUser();
        if (!friend.getReceiver().getUid().equals(userSecurity.getUid()) && !friend.getSender().getUid().equals(userSecurity.getUid())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (friend.getFriendStatus() != FriendStatus.PENDING) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        friend.setFriendStatus(FriendStatus.ACCEPT);
        return ResponseEntity.ok(friendService.saveRequest(friend));
    }

    @Transactional
    @PostMapping("/decline")
    public ResponseEntity<Friend> declineRequest(@RequestBody Friend friend) {
        User userSecurity = securityService.getUser();
        if (!friend.getReceiver().getUid().equals(userSecurity.getUid()) && !friend.getSender().getUid().equals(userSecurity.getUid())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        friendService.deleteFriend(friend);
        return ResponseEntity.ok(friend);
    }
}
