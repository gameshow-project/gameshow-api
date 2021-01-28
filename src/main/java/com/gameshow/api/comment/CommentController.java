package com.gameshow.api.comment;

import com.gameshow.api.security.SecurityService;
import com.gameshow.api.user.User;
import com.gameshow.api.user.UserNotFoundException;
import com.gameshow.api.user.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    private final SecurityService securityService;

    private final UserService userService;

    private final CommentConverter commentConverter;

    @GetMapping("game/{gameId}")
    public ResponseEntity<Page<CommentDto>> findAllByGame(@PathVariable("gameId") Long gameId,
                                                       @RequestParam("page") int page,
                                                       @RequestParam("limit") int limit,
                                                       @RequestParam("order") String order) {
        return ResponseEntity.ok(commentService.findAllByGame(gameId, page, limit, order));
    }

    @PostMapping
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto) throws UserNotFoundException, CommentNotFound {
        User userSecurity = securityService.getUser();
        User user = userService.findById(userSecurity.getUid());
        Comment comment = commentConverter.dtoToEntity(commentDto);
        comment.setAuthor(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.saveComment(comment));
    }

    @GetMapping("/responses/{parentId}")
    public ResponseEntity<Page<CommentDto>> getChildren(@PathVariable("parentId") Long parentId,
                                                        @RequestParam("page") int page,
                                                        @RequestParam("limit") int limit) {
        return ResponseEntity.ok(commentService.getChildren(parentId, page, limit));
    }

    @Transactional
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) throws CommentNotFound {
        User userSecurity = securityService.getUser();
        Comment comment = commentService.getById(commentId);
        if (!userSecurity.getUid().equals(comment.getAuthor().getUid())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/like/{commentId}")
    public ResponseEntity<CommentDto> likeComment(@PathVariable("commentId") Long id, @RequestBody User user) throws CommentNotFound {
        return ResponseEntity.ok(commentService.likeComment(id, user));
    }

    @PostMapping("/dislike/{commentId}/{userId}")
    public ResponseEntity<CommentDto> dislikeComment(@PathVariable("commentId") Long commentId, @PathVariable("userId") String userId) throws CommentNotFound {
        return ResponseEntity.ok(commentService.dislikeComment(commentId, userId));
    }

}
