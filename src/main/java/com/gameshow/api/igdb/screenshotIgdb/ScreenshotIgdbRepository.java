package com.gameshow.api.igdb.screenshotIgdb;

import com.gameshow.api.igdb.IgbdAccessToken;
import com.gameshow.api.shared.Cover;
import com.gameshow.api.shared.Platform;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ScreenshotIgdbRepository {

    @Value("${igdb.url}")
    private String url;

    private final IgbdAccessToken igbdAccessToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public ScreenshotIgdbRepository(IgbdAccessToken igbdAccessToken) {
        this.igbdAccessToken = igbdAccessToken;
    }

    public List<Cover> getScreenshotsByGame(Long gameId) {
        String body = "fields *;" +
                "where game = " + gameId + ";";
        HttpEntity<String> httpEntity = new HttpEntity<>(body, igbdAccessToken.getHeaders());
        Cover[] covers = restTemplate.postForObject(url + "/screenshots",  httpEntity, Cover[].class);
        return Arrays.asList(covers);
    }

}
