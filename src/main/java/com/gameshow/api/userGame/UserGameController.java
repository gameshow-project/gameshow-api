package com.gameshow.api.userGame;

import com.gameshow.api.security.SecurityService;
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

    private final SecurityService securityService;

    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserGame>> getGamesUser(@PathVariable("id") String id) throws UserNotFoundException {
        User user = userService.findById(id);
        return ResponseEntity.ok(userGameService.getGamesUser(id));
    }

    @PostMapping("/add_game")
    public ResponseEntity<UserGame> addGameInUser(@RequestBody Game game) {
        User user = securityService.getUser();
        UserGame userGame = UserGame.builder()
                .game(game)
                .user(user)
                .nbFinished(0)
                .platinum(false).build();
        return ResponseEntity.ok(this.userGameService.addGameForUser(userGame));
    }

    @Transactional
    @DeleteMapping("/remove_game/{gameId}")
    public ResponseEntity<?> deletePlatformForUser(HttpServletRequest request, @PathVariable("gameId") Long gameId) {
        User user = securityService.getUser();
        UserGameId userGameId = new UserGameId();
        userGameId.setGame(gameId);
        userGameId.setUser(user.getUid());
        userGameService.removeGameForUser(userGameId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/platinum/{gameId}")
    public ResponseEntity<UserGame> setPlatinum(HttpServletRequest request, @PathVariable("gameId") Long gameId) throws UserGameNotFoundException {
        User user = securityService.getUser();
        UserGameId userGameId = new UserGameId();
        userGameId.setGame(gameId);
        userGameId.setUser(user.getUid());
        return ResponseEntity.ok(userGameService.setPlatinum(userGameId));
    }

    @PostMapping("/finish/{gameId}")
    public ResponseEntity<UserGame> setFinished(@PathVariable("gameId") Long gameId, @RequestBody int nbFinished) throws UserGameNotFoundException {
        User user = securityService.getUser();
        UserGameId userGameId = new UserGameId();
        userGameId.setGame(gameId);
        userGameId.setUser(user.getUid());
        return ResponseEntity.ok(userGameService.setFinished(userGameId, nbFinished));
    }

}
