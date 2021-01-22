package com.gameshow.api.discord;

import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiscordService {

    private final DiscordRepository discordRepository;

    public void sendRequestTesteur(MessageDiscord messageDiscord) throws JSONException {
        this.discordRepository.sendRequestTesteur(messageDiscord);
    }

    public void sendTicket(MessageDiscord messageDiscord) throws JSONException {
        this.discordRepository.sendTicket(messageDiscord);
    }

}
