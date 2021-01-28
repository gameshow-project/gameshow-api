package com.gameshow.api.comment;

import com.gameshow.api.user.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentConverter commentConverter;

    public Page<CommentDto> findAllByGame(Long gameId, int offset, int limit, String order) {
        Sort sort = order.equals("like") ? Sort.by(order).ascending() : Sort.by(order).descending();
        Pageable pageable = PageRequest.of(offset, limit, sort);
        return commentConverter.pageEntityToDto(commentRepository.findAllByGameIdAndParentNull(gameId, pageable));
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment getById(Long commentId) throws CommentNotFound {
        return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFound(commentId));
    }

    public CommentDto saveComment(Comment comment) {
        LocalDateTime now = LocalDateTime.now();
        comment.setDate(now.toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(now)));
        return commentConverter.entityToDto(commentRepository.save(comment));
    }

    public CommentDto likeComment(Long commentId, User user) throws CommentNotFound {
        Comment comment = this.getById(commentId);
        comment.getLike().add(user);
        return commentConverter.entityToDto(commentRepository.save(comment));
    }

    public CommentDto dislikeComment(Long commentId, String userId) throws CommentNotFound {
        Comment comment = this.getById(commentId);
        comment.getLike().removeIf(user -> user.getUid().equals(userId));
        return commentConverter.entityToDto(commentRepository.save(comment));
    }

    public Page<CommentDto> getChildren(Long parentId, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("date").descending());
        return commentConverter.pageEntityToDto(commentRepository.findAllByParentId(parentId, pageable));
    }

}
