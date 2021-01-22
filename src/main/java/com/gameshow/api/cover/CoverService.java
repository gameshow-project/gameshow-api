package com.gameshow.api.cover;

import com.gameshow.api.shared.Cover;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoverService {

    private final CoverRepository coverRepository;

    public Cover saveCover(Cover cover) {
        return this.coverRepository.save(cover);
    }


}
