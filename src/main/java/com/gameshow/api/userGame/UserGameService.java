package com.gameshow.api.userGame;

import com.gameshow.api.activity.ActivityService;
import com.gameshow.api.activity.Category;
import com.gameshow.api.game.GameService;
import com.gameshow.api.user.UserService;
import com.gameshow.api.userPlatform.UserPlatform;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserGameService {

    private final UserGameRepository userGameRepository;

    private final ActivityService activityService;

    private final GameService gameService;

    public UserGame addGameForUser(UserGame userGame) {
        this.gameService.saveGame(userGame.getGame());
        UserGame userGameSave = this.userGameRepository.save(userGame);
        activityService.createActivity(userGame.getUser(), userGame.getGame(), null, Category.ADD_GAME);
        return userGameSave;
    }

    public List<UserGame> getGamesUser(Long id) {
        return this.userGameRepository.findAllByUserId(id);
    }

    public void removeGameForUser(UserGameId userGameId) {
        UserGame userGame = userGameRepository.getOne(userGameId);
        activityService.createActivity(userGame.getUser(), userGame.getGame(), null, Category.REMOVE_GAME);
        this.userGameRepository.deleteById(userGameId);
    }

    public UserGame setPlatinum(UserGameId userGameId) throws UserGameNotFoundException {
        UserGame userGame = userGameRepository.findById(userGameId).orElseThrow(() -> new UserGameNotFoundException(userGameId));
        userGame.setPlatinum(!userGame.isPlatinum());
        return userGameRepository.save(userGame);
    }

    public UserGame setFinished(UserGameId userGameId, int nbFinish) throws UserGameNotFoundException {
        UserGame userGame = userGameRepository.findById(userGameId).orElseThrow(() -> new UserGameNotFoundException(userGameId));
        userGame.setNbFinished(nbFinish);
        return userGameRepository.save(userGame);
    }

}
