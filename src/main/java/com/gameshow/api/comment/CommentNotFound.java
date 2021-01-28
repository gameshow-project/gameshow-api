package com.gameshow.api.comment;

public class CommentNotFound extends Exception {

    CommentNotFound(Long commentId) {
        super("Comment " + commentId + " not found");
    }

}
