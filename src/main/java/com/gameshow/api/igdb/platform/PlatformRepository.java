package com.gameshow.api.igdb.platform;

import com.gameshow.api.igdb.IgbdAccessToken;
import com.gameshow.api.shared.Platform;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class PlatformRepository {

    @Value("${igdb.url}")
    private String url;

    private final IgbdAccessToken igbdAccessToken;

    public PlatformRepository(IgbdAccessToken igbdAccessToken) {
        this.igbdAccessToken = igbdAccessToken;
    }

    public List<Platform> findPlatformByPlatformFamily(String platformFamily) {
        RestTemplate restTemplate = new RestTemplate();
        String body = "fields *, platform_family.*;" +
                "where platform_family = (" + platformFamily + ") & category = (1,5);" +
                "sort platform_family asc;" +
                "limit 500;";
        HttpEntity<String> httpEntity = new HttpEntity<String>(body, igbdAccessToken.getHeaders());
        Platform[] platforms = restTemplate.postForObject(url + "/platforms",  httpEntity, Platform[].class);
        return Arrays.asList(platforms);
    }

}
