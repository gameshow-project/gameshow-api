package com.gameshow.api.twitch.twitchStream;

import com.gameshow.api.igdb.IgbdAccessToken;
import com.gameshow.api.shared.Game;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class TwitchStreamRepository {

    @Value("${twitch.url}")
    private String url;

    private final IgbdAccessToken igbdAccessToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public TwitchStreamRepository(IgbdAccessToken igbdAccessToken) {
        this.igbdAccessToken = igbdAccessToken;
    }

    public List<TwitchStream> getStreamsByGame(String game) {
        HttpEntity entity = new HttpEntity(igbdAccessToken.getHeadersTwitch());
        Map<String, String> params = new HashMap<>();
        params.put("game", game);
        params.put("language", "fr");
        return Objects.requireNonNull(restTemplate.exchange(url + "/streams?game={game}&language={lang}", HttpMethod.GET, entity, TwitchStreams.class, game, "fr").getBody()).getStreams();
    }

}
