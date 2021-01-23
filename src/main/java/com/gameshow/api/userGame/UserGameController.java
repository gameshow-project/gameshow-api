package com.gameshow.api.userGame;

import com.gameshow.api.auth.AuthNotFoundException;
import com.gameshow.api.auth.AuthService;
import com.gameshow.api.config.security.jwt.JWTFilter;
import com.gameshow.api.config.security.jwt.TokenProvider;
import com.gameshow.api.shared.Game;
import com.gameshow.api.user.User;
import com.gameshow.api.user.UserNotFoundException;
import com.gameshow.api.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user_game")
public class UserGameController {

    private final UserGameService userGameService;

    private final TokenProvider tokenProvider;

    private final AuthService authService;

    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserGame>> getGamesUser(@PathVariable("id") Long id) throws UserNotFoundException {
        User user = userService.findById(id);
        return ResponseEntity.ok(userGameService.getGamesUser(id));
    }

    @PostMapping("/add_game")
    public ResponseEntity<UserGame> addGameInUser(HttpServletRequest request,
                                                  @RequestBody Game game) throws AuthNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        UserGame userGame = UserGame.builder()
                .game(game)
                .user(user)
                .nbFinished(0)
                .platinum(false).build();
        return ResponseEntity.ok(this.userGameService.addGameForUser(userGame));
    }

    @Transactional
    @DeleteMapping("/remove_game/{gameId}")
    public ResponseEntity<?> deletePlatformForUser(HttpServletRequest request, @PathVariable("gameId") Long gameId) throws AuthNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        UserGameId userGameId = new UserGameId();
        userGameId.setGame(gameId);
        userGameId.setUser(user.getId());
        userGameService.removeGameForUser(userGameId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/platinum/{gameId}")
    public ResponseEntity<UserGame> setPlatinum(HttpServletRequest request, @PathVariable("gameId") Long gameId) throws AuthNotFoundException, UserGameNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        UserGameId userGameId = new UserGameId();
        userGameId.setGame(gameId);
        userGameId.setUser(user.getId());
        return ResponseEntity.ok(userGameService.setPlatinum(userGameId));
    }

    @PostMapping("/finish/{gameId}")
    public ResponseEntity<UserGame> setFinished(HttpServletRequest request, @PathVariable("gameId") Long gameId, @RequestBody int nbFinished) throws AuthNotFoundException, UserGameNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        UserGameId userGameId = new UserGameId();
        userGameId.setGame(gameId);
        userGameId.setUser(user.getId());
        return ResponseEntity.ok(userGameService.setFinished(userGameId, nbFinished));
    }

}
