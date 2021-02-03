package com.gameshow.api.Friend;

import com.gameshow.api.user.User;
import com.gameshow.api.userPlatform.UserPlatformId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@IdClass(FriendId.class)
@Table(name = "friend")
public class Friend {

    @Id
    @ManyToOne
    private User sender;

    @Id
    @ManyToOne
    private User receiver;

    @Enumerated(EnumType.STRING)
    private FriendStatus friendStatus;


}
