package com.gameshow.api.platformLogo;

import com.gameshow.api.shared.PlatformLogo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlatformLogoService {

    private final PlatformLogoRepository platformLogoRepository;

    public PlatformLogo savePlatformLogo(PlatformLogo platformLogo) {
        return this.platformLogoRepository.save(platformLogo);
    }

}
