package com.gameshow.api.game;

import com.gameshow.api.dto.GameDetails;
import com.gameshow.api.security.SecurityService;
import com.gameshow.api.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    private final SecurityService securityService;

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDetails> getGame(@PathVariable("gameId") Long gameId) throws GameNotFoundException {
        User user = securityService.getUser();
        return ResponseEntity.ok(gameService.getGame(gameId, user.getUid()));
    }

}
