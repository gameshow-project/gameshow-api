package com.gameshow.api.igdb.screenshotIgdb;

import com.gameshow.api.shared.Cover;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScreenshotIgdbService {

    private final ScreenshotIgdbRepository screenshotIgdbRepository;

    public List<Cover> getScreenshotsByGame(Long gameId) {
        return this.screenshotIgdbRepository.getScreenshotsByGame(gameId);
    }

}
