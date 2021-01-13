package com.gameshow.api.platformFamily;

import com.gameshow.api.shared.PlatformFamily;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlatformFamilyService {

    private final PlatformFamilyRepository platformFamilyRepository;

    public PlatformFamily savePlatformFamily(PlatformFamily platformFamily) {
        return this.platformFamilyRepository.save(platformFamily);
    }

    public boolean existById(Long id) {
        return this.platformFamilyRepository.existsById(id);
    }

}
