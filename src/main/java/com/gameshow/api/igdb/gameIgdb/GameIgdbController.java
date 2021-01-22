package com.gameshow.api.igdb.gameIgdb;

import com.gameshow.api.shared.Game;
import com.gameshow.api.shared.Platform;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@CrossOrigin
@Controller
@AllArgsConstructor
@RequestMapping("/api/game_igdb")
public class GameIgdbController {

    private final GameIgdbService gameIgdbService;

    @GetMapping("/recently_released")
    public ResponseEntity<List<Game>> getRecentlyReleased() {
        return ResponseEntity.ok(this.gameIgdbService.recentlyReleased());
    }

    @GetMapping("/lot_rating_count")
    public ResponseEntity<List<Game>> getLotRatingCount() {
        return ResponseEntity.ok(this.gameIgdbService.lotRatingCount());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Game>> researchGame(@RequestParam("q") String research) {
        return ResponseEntity.ok(gameIgdbService.researchGame(research));
    }

    @GetMapping("/discover")
    public ResponseEntity<Discover> getDiscover() {
        return ResponseEntity.ok(gameIgdbService.getDiscover());
    }

}
