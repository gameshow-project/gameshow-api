package com.gameshow.api.igdb.platformIgdb;

import com.gameshow.api.shared.Platform;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformIgdbService {

    private final PlatformIgdbRepository platformIgdbRepository;

    public List<Platform> findPlatformsByPlatformFamily(String platformFamily) {
        return this.platformIgdbRepository.findPlatformByPlatformFamily(platformFamily);
    }

    public List<Platform> researchPlatform(String research) {
        return this.platformIgdbRepository.researchPlatform(research);
    }

}
