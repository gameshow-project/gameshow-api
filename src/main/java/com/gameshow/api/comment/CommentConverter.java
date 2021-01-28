package com.gameshow.api.comment;

import com.gameshow.api.converter.AbstractConverter;
import com.gameshow.api.security.SecurityService;
import com.gameshow.api.user.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@AllArgsConstructor
public class CommentConverter implements AbstractConverter<Comment, CommentDto> {

    private final CommentRepository commentRepository;

    private final SecurityService securityService;

    @Override
    public CommentDto entityToDto(Comment entity) {
        User userSecurity = securityService.getUser();
        CommentDto commentDto = CommentDto.builder()
                .id(entity.getId())
                .author(entity.getAuthor())
                .comment(entity.getComment())
                .date(entity.getDate())
                .id(entity.getId())
                .game(entity.getGame())
                .parent(entity.getParent() == null ? null : entityToDto(entity.getParent())).build();
        commentDto.setNbComment(commentRepository.countByParentId(entity.getId()));
        commentDto.setNbLike(entity.getLike().size());
        commentDto.setAlreadyLike(entity.getLike().stream().anyMatch(c -> c.getUid().equals(userSecurity.getUid())));
        commentDto.setMyComment(entity.getAuthor().getUid().equals(userSecurity.getUid()));
        return commentDto;
    }

    @Override
    public Comment dtoToEntity(CommentDto dto) {
        return Comment.builder()
                .author(dto.getAuthor())
                .comment(dto.getComment())
                .date(dto.getDate())
                .parent(dto.getParent() == null ? null : dtoToEntity(dto.getParent()))
                .game(dto.getGame())
                .id(dto.getId())
                .like(new HashSet<>()).build();
    }

}
