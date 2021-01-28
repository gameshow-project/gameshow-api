package com.gameshow.api.dto;

import com.gameshow.api.comment.Comment;
import com.gameshow.api.comment.CommentDto;
import com.gameshow.api.shared.Game;
import com.gameshow.api.userGame.UserGame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDetails {

    private Game game;
    private UserGame userGame;
    private int nbFollowed;
    private Page<CommentDto> comments;
}
