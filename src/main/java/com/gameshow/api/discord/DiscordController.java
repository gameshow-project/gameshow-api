package com.gameshow.api.discord;

import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/discord")
public class DiscordController {

    private final DiscordService discordService;

    @PostMapping("/sendRequest")
    public ResponseEntity<?> sendRequest(@RequestBody MessageDiscord messageDiscord) throws JSONException {
        this.discordService.sendRequestTesteur(messageDiscord);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sendTicket")
    public ResponseEntity<?> sendTicket(@RequestBody MessageDiscord messageDiscord) throws JSONException {
        this.discordService.sendTicket(messageDiscord);
        return ResponseEntity.ok().build();
    }

}
