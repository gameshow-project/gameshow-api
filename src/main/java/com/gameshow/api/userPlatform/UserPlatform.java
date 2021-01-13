package com.gameshow.api.userPlatform;

import com.gameshow.api.shared.Platform;
import com.gameshow.api.user.User;
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
@Table(name = "user_platform")
@IdClass(UserPlatformId.class)
public class UserPlatform {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;

    private int quantity;
}
