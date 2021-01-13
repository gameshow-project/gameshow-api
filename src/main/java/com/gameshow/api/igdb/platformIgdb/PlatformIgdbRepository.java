package com.gameshow.api.igdb.platformIgdb;

import com.gameshow.api.igdb.IgbdAccessToken;
import com.gameshow.api.shared.Platform;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class PlatformIgdbRepository {

    @Value("${igdb.url}")
    private String url;

    private final IgbdAccessToken igbdAccessToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public PlatformIgdbRepository(IgbdAccessToken igbdAccessToken) {
        this.igbdAccessToken = igbdAccessToken;
    }

    public List<Platform> findPlatformByPlatformFamily(String platformFamily) {
        String body = "fields *, platform_family.*;" +
                "where platform_family = (" + platformFamily + ") & category = (1,5);" +
                "sort platform_family asc;" +
                "limit 500;";
        HttpEntity<String> httpEntity = new HttpEntity<>(body, igbdAccessToken.getHeaders());
        Platform[] platforms = restTemplate.postForObject(url + "/platforms",  httpEntity, Platform[].class);
        return Arrays.asList(platforms);
    }

    public List<Platform> researchPlatform(String research) {
        String body = "fields *, platform_family.*;" +
                "search \"" + research + "\";" +
                "where category = (1,5);" +
                "limit 500;";
        HttpEntity<String> httpEntity = new HttpEntity<>(body, igbdAccessToken.getHeaders());
        Platform[] platforms = restTemplate.postForObject(url + "/platforms",  httpEntity, Platform[].class);
        return Arrays.asList(platforms);
    }

}
