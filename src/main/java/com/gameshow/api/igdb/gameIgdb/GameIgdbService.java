package com.gameshow.api.igdb.gameIgdb;

import com.gameshow.api.shared.Game;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameIgdbService {

    private final GameIgdbRepository gameIgdbRepository;

    public List<Game> recentlyReleased() {
        return this.gameIgdbRepository.recentlyReleasedGames();
    }

    public Discover getDiscover() {
        return Discover.builder()
                .recentlyRelease(recentlyReleased())
                .build();
    }

    public List<Game> lotRatingCount() {
        return this.gameIgdbRepository.gameWIthLotTotalRating();
    }

}
