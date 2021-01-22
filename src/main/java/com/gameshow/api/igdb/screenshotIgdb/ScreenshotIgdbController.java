package com.gameshow.api.igdb.screenshotIgdb;

import com.gameshow.api.shared.Cover;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/screenshot_igdb")
public class ScreenshotIgdbController {

    private final ScreenshotIgdbService screenshotIgdbService;

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Cover>> getScreenshotsByGame(@PathVariable("gameId") Long gameId) {
        return ResponseEntity.ok(screenshotIgdbService.getScreenshotsByGame(gameId));
    }

}
