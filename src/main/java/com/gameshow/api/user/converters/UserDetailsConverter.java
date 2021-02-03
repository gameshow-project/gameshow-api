package com.gameshow.api.user.converters;

import com.gameshow.api.Friend.Friend;
import com.gameshow.api.Friend.FriendService;
import com.gameshow.api.comment.CommentNotFound;
import com.gameshow.api.converter.AbstractConverter;
import com.gameshow.api.security.SecurityService;
import com.gameshow.api.user.User;
import com.gameshow.api.user.models.UserDetailsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

@Component
@AllArgsConstructor
public class UserDetailsConverter implements AbstractConverter<User, UserDetailsDto> {

    private final SecurityService securityService;
    private final FriendService friendService;

    @Override
    public UserDetailsDto entityToDto(User entity) {
        Friend friend;
        if (securityService.getUser().getUid().equals(entity.getUid())) {
            friend = null;
        }else {
            if (friendService.alreadyRequest(securityService.getUser().getUid(), entity.getUid())) {
                friend = friendService.getRequestOrNull(securityService.getUser().getUid(), entity.getUid());
            }else {
                friend = null;
            }
        }
        return UserDetailsDto.builder()
                .account(entity.getAccount())
                .banner(entity.getBanner())
                .bio(entity.getBio())
                .email(entity.getEmail())
                .firstname(entity.getFirstname())
                .isEmailVerified(entity.isEmailVerified())
                .issuer(entity.getIssuer())
                .lastname(entity.getLastname())
                .myProfil(securityService.getUser().getUid().equals(entity.getUid()))
                .name(entity.getName())
                .picture(entity.getPicture())
                .userGames(entity.getUserGames())
                .username(entity.getUsername())
                .uid(entity.getUid())
                .userPlatforms(entity.getUserPlatforms())
                .friend(friend).build();
    }

    @Override
    public User dtoToEntity(UserDetailsDto dto) throws CommentNotFound {
        return null;
    }
}
