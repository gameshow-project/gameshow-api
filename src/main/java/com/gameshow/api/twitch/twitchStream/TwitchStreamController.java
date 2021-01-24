package com.gameshow.api.twitch.twitchStream;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/twitch_stream")
public class TwitchStreamController {

    private final TwitchStreamService twitchStreamService;

    @GetMapping
    public ResponseEntity<List<TwitchStream>> getStreamsByGame(@RequestParam("game") String game){
        return ResponseEntity.ok(twitchStreamService.getStreamsByGame(game));
    }

}
