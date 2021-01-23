package com.gameshow.api.igdb.gameIgdb;

import com.gameshow.api.game.GameNotFoundException;
import com.gameshow.api.igdb.IgbdAccessToken;
import com.gameshow.api.shared.Game;
import com.gameshow.api.shared.Platform;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

@Component
public class GameIgdbRepository {

    @Value("${igdb.url}")
    private String url;

    private final IgbdAccessToken igbdAccessToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public GameIgdbRepository(IgbdAccessToken igbdAccessToken) {
        this.igbdAccessToken = igbdAccessToken;
    }

    public List<Game> recentlyReleasedGames() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.minusMonths(3);
        String body = "fields *, cover.*, collection.*, collection.games.*;" +
                "where parent_game = null & version_parent = null & first_release_date < " + localDateTime.toEpochSecond(ZoneOffset.UTC) + " & platforms = [167, 169];" +
                "sort first_release_date desc;" +
                "limit 20;";
        HttpEntity<String> httpEntity = new HttpEntity<>(body, igbdAccessToken.getHeaders());
        Game[] games = restTemplate.postForObject(url + "/games",  httpEntity, Game[].class);
        return Arrays.asList(games);
    }

    public List<Game> gameWIthLotTotalRating() {
        String body = "fields *, cover.*, platforms.*, platforms.platform_family.*, platforms.platform_logo.*, similar_games.cover.*, similar_games.name, screenshots.*, videos.*;" +
                "where parent_game = null & version_parent = null & rating_count > 1000;" +
                "exclude collection.games.collection, collection.games.cover;" +
                "sort rating_count desc;" +
                "limit 20;";
        HttpEntity<String> httpEntity = new HttpEntity<>(body, igbdAccessToken.getHeaders());
        Game[] games = restTemplate.postForObject(url + "/games",  httpEntity, Game[].class);
        return Arrays.asList(games);
    }

    public List<Game> researchPlatform(String research) {
        String body = "fields *, cover.*, platforms.*, platforms.platform_family.*, platforms.platform_logo.*, similar_games.cover.*, similar_games.name, screenshots.*, videos.*;" +
                "search \"" + research + "\";" +
                "where category = (0,4) & version_parent = null;" +
                "exclude collection.games.collection, collection.games.cover;" +
                "limit 500;";
        HttpEntity<String> httpEntity = new HttpEntity<>(body, igbdAccessToken.getHeaders());
        Game[] games = restTemplate.postForObject(url + "/games",  httpEntity, Game[].class);
        return Arrays.asList(games);
    }

    public Game findById(Long gameId) throws GameNotFoundException {
        String body = "fields *, cover.*, platforms.*, platforms.platform_family.*, platforms.platform_logo.*, similar_games.cover.*, similar_games.name, screenshots.*, videos.*;" +
                "where id = " + gameId + ";";
                HttpEntity<String> httpEntity = new HttpEntity<>(body, igbdAccessToken.getHeaders());
        Game[] games = restTemplate.postForObject(url + "/games", httpEntity, Game[].class);
        if (games == null || games.length < 1) {
            throw new GameNotFoundException(gameId);
        }
        return games[0];
    }

}
