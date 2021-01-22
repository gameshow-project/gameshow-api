package com.gameshow.api.otherGame;

import com.gameshow.api.cover.CoverService;
import com.gameshow.api.shared.OtherGame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OtherGameService {

    private final OtherGameRepository otherGameRepository;

    private final CoverService coverService;

    public OtherGame saveOtherGame(OtherGame otherGame) {
        if (otherGame.getCover() != null) {
            coverService.saveCover(otherGame.getCover());
        }
        return otherGameRepository.save(otherGame);
    }

}
