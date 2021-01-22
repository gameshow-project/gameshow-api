package com.gameshow.api.game;

import com.gameshow.api.auth.AuthNotFoundException;
import com.gameshow.api.auth.AuthService;
import com.gameshow.api.config.security.jwt.JWTFilter;
import com.gameshow.api.config.security.jwt.TokenProvider;
import com.gameshow.api.dto.GameDetails;
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

    private final TokenProvider tokenProvider;

    private final AuthService authService;

    private final GameService gameService;

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDetails> getGame(HttpServletRequest request, @PathVariable("gameId") Long gameId) throws AuthNotFoundException, GameNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        return ResponseEntity.ok(gameService.getGame(gameId, user.getId()));
    }

}
