package com.gameshow.api.comment;

import com.gameshow.api.shared.Game;
import com.gameshow.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    private String comment;
    private CommentDto parent;
    private int nbComment;
    private Game game;
    private int nbLike;
    private User author;
    private Long date;
    private boolean alreadyLike;
    private boolean isMyComment;

}
