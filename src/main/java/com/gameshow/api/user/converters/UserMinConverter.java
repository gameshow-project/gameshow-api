package com.gameshow.api.user.converters;

import com.gameshow.api.comment.CommentNotFound;
import com.gameshow.api.converter.AbstractConverter;
import com.gameshow.api.user.User;
import com.gameshow.api.user.models.UserMinDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMinConverter implements AbstractConverter<User, UserMinDto> {

    @Override
    public UserMinDto entityToDto(User entity) {
        return UserMinDto.builder()
                .banner(entity.getBanner())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .uid(entity.getUid())
                .username(entity.getUsername())
                .visibility(entity.getVisibility()).build();
    }

    @Override
    public User dtoToEntity(UserMinDto dto) throws CommentNotFound {
        return null;
    }

}
