package com.gameshow.api.discord;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gameshow.api.shared.Game;
import lombok.AllArgsConstructor;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DiscordRepository {

    @Value("${discord.webhook_request}")
    private String urlRequest;

    @Value("${discord.webhook_ticket}")
    private String urlTicket;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendRequestTesteur(MessageDiscord messageDiscord) throws JSONException {
        JSONObject message = new JSONObject();
        message.put("title", messageDiscord.getTitle());
        message.put("description", messageDiscord.getDescription());
        message.put("timestamp", LocalDateTime.now());
        JSONArray array = new JSONArray();
        array.put(message);
        JSONObject body = new JSONObject();
        body.put("embeds", array);
        HttpEntity<String> httpEntity = new HttpEntity<String>(body.toString(), this.getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(this.urlRequest, HttpMethod.POST,  httpEntity, String.class);
    }

    public void sendTicket(MessageDiscord messageDiscord) throws JSONException {
        JSONObject message = new JSONObject();
        message.put("title", messageDiscord.getTitle());
        message.put("description", messageDiscord.getDescription());
        message.put("timestamp", LocalDateTime.now());
        JSONArray array = new JSONArray();
        array.put(message);
        JSONObject body = new JSONObject();
        body.put("embeds", array);
        HttpEntity<String> httpEntity = new HttpEntity<String>(body.toString(), this.getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(this.urlTicket, HttpMethod.POST,  httpEntity, String.class);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        return requestHeaders;
    }

}
