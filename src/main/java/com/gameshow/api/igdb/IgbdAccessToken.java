package com.gameshow.api.igdb;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
@NoArgsConstructor
@Slf4j
public class IgbdAccessToken {

    @Value("${igdb.urlToken}")
    private String url;

    @Value("${igdb.client_id}")
    private String clientId;

    @Value("${igdb.client_secret}")
    private String clientSecret;

    private String token = "";

    @Scheduled(fixedDelay = 5439352)
    public void refreshToken() {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("client_id", this.clientId)
                .queryParam("client_secret", this.clientSecret)
                .queryParam("grant_type", "client_credentials");
        IgdbToken igdbToken = restTemplate.postForObject(uri.toUriString(), null,  IgdbToken.class);
        log.info("REFRESH TOKEN " + igdbToken.getAccess_token());
        this.token = igdbToken.getAccess_token();
    }

    public HttpHeaders getHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setBearerAuth(this.token);
        requestHeaders.setContentType(MediaType.TEXT_PLAIN);
        requestHeaders.set("Client-ID", this.clientId);
        return requestHeaders;
    }

    public HttpHeaders getHeadersTwitch() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Client-ID", this.clientId);
        requestHeaders.set("Accept", "application/vnd.twitchtv.v5+json");
        return requestHeaders;
    }

}
