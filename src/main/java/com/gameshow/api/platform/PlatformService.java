package com.gameshow.api.platform;

import com.gameshow.api.platformFamily.PlatformFamilyService;
import com.gameshow.api.platformLogo.PlatformLogoService;
import com.gameshow.api.shared.Platform;
import com.gameshow.api.user.User;
import com.gameshow.api.user.UserNotFoundException;
import com.gameshow.api.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;

    private final PlatformFamilyService platformFamilyService;

    private final PlatformLogoService platformLogoService;

    public Platform savePlatform(Platform platform) {
        if(platform.getPlatform_logo() != null) {
            platformLogoService.savePlatformLogo(platform.getPlatform_logo());
        }
        if (platform.getPlatform_family() != null) {
            platformFamilyService.savePlatformFamily(platform.getPlatform_family());
        }
        return this.platformRepository.save(platform);
    }

    public boolean existById(Long id) {
        return this.platformRepository.existsById(id);
    }

}
