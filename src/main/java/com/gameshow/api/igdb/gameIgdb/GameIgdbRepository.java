package com.gameshow.api.igdb.gameIgdb;

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
        String body = "fields *, cover.*;" +
                "where parent_game = null & version_parent = null & first_release_date < " + localDateTime.toEpochSecond(ZoneOffset.UTC) + " & platforms = [167, 169];" +
                "sort first_release_date desc;" +
                "limit 20;";
        HttpEntity<String> httpEntity = new HttpEntity<>(body, igbdAccessToken.getHeaders());
        Game[] games = restTemplate.postForObject(url + "/games",  httpEntity, Game[].class);
        return Arrays.asList(games);
    }

    public List<Game> gameWIthLotTotalRating() {
        String body = "fields *, cover.*;" +
                "where parent_game = null & version_parent = null & rating_count > 1000;" +
                "sort rating_count desc;" +
                "limit 20;";
        HttpEntity<String> httpEntity = new HttpEntity<>(body, igbdAccessToken.getHeaders());
        Game[] games = restTemplate.postForObject(url + "/games",  httpEntity, Game[].class);
        return Arrays.asList(games);
    }

}
