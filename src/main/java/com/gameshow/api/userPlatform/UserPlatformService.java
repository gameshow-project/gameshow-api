package com.gameshow.api.userPlatform;

import com.gameshow.api.platform.PlatformService;
import com.gameshow.api.shared.Platform;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserPlatformService {

    private final UserPlatformRepository userPlatformRepository;

    private final PlatformService platformService;

    public UserPlatform addPlatformForUser(UserPlatform userPlatform) {
        if (!platformService.existById(userPlatform.getPlatform().getId())) {
            this.platformService.savePlatform(userPlatform.getPlatform());
        }
        return this.userPlatformRepository.save(userPlatform);
    }

    public void deletePlatformForUser(Long platformId, Long userId) {
        this.userPlatformRepository.deleteByPlatformIdAndUserId(platformId, userId);
    }

    public List<UserPlatform> getPlatformsUser(Long id) {
        return userPlatformRepository.findAllByUserId(id);
    }

    public UserPlatform changeQuantity(Long userId, Long platformId, int value) {
        UserPlatformId userPlatformId = new UserPlatformId();
        userPlatformId.setPlatform(platformId);
        userPlatformId.setUser(userId);
        UserPlatform userPlatform = userPlatformRepository.getOne(userPlatformId);
        userPlatform.setQuantity(userPlatform.getQuantity() + value);
        if (userPlatform.getQuantity() <= 0) {
            return null;
        }
        return userPlatformRepository.save(userPlatform);
    }

}
