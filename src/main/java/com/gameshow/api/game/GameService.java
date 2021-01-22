package com.gameshow.api.game;

import com.gameshow.api.cover.CoverRepository;
import com.gameshow.api.cover.CoverService;
import com.gameshow.api.dto.GameDetails;
import com.gameshow.api.igdb.gameIgdb.GameIgdbRepository;
import com.gameshow.api.otherGame.OtherGameService;
import com.gameshow.api.platform.PlatformService;
import com.gameshow.api.shared.Game;
import com.gameshow.api.userGame.UserGame;
import com.gameshow.api.userGame.UserGameId;
import com.gameshow.api.userGame.UserGameRepository;
import com.gameshow.api.video.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    private final CoverRepository coverRepository;

    private final PlatformService platformService;

    private final OtherGameService otherGameService;

    private final VideoRepository videoRepository;

    private final GameIgdbRepository gameIgdbRepository;

    private final UserGameRepository userGameRepository;

    public Game findById(Long id) throws GameNotFoundException {
        return gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

    public Game saveGame(Game game) {
        if (game.getCover() != null) {
            coverRepository.save(game.getCover());
        }
        if (game.getPlatforms() != null && !game.getPlatforms().isEmpty()) {
            game.getPlatforms().forEach(platformService::savePlatform);
        }
        if(game.getSimilar_games() != null && !game.getSimilar_games().isEmpty()) {
            game.getSimilar_games().forEach(otherGameService::saveOtherGame);
        }
        if(game.getScreenshots() != null) {
            coverRepository.saveAll(game.getScreenshots());
        }
        if(game.getVideos() != null) {
            videoRepository.saveAll(game.getVideos());
        }
        return this.gameRepository.save(game);
    }

    public GameDetails getGame(Long gameId, Long userId) throws GameNotFoundException {
        GameDetails gameDetails = new GameDetails();
        if (!gameRepository.existsById(gameId))  {
            gameRepository.save(this.gameIgdbRepository.findById(gameId));
        }
        gameDetails.setGame(this.findById(gameId));
        gameDetails.setNbFollowed(userGameRepository.countByGame_Id(gameId));
        UserGameId userGameId = new UserGameId();
        userGameId.setGame(gameId);
        userGameId.setUser(userId);
        if (userGameRepository.existsById(userGameId)) {
            UserGame userGame = userGameRepository.getOne(userGameId);
            userGame.setGame(null);
            gameDetails.setUserGame(userGame);
        }
        return gameDetails;
    }

}
