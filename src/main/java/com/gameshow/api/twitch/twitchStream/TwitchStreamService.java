package com.gameshow.api.twitch.twitchStream;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TwitchStreamService {

    private final TwitchStreamRepository twitchStreamRepository;

    public List<TwitchStream> getStreamsByGame(String game) {
        return twitchStreamRepository.getStreamsByGame(game);
    }

}
