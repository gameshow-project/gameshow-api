package com.gameshow.api.igdb.platform;

import com.gameshow.api.shared.Platform;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;

    public List<Platform> findPlatformsByPlatformFamily(String platformFamily) {
        return this.platformRepository.findPlatformByPlatformFamily(platformFamily);
    }

}
