package com.gameshow.api.userPlatform;

import com.gameshow.api.activity.ActivityService;
import com.gameshow.api.activity.Category;
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

    private final ActivityService activityService;

    private final PlatformService platformService;

    public UserPlatform addPlatformForUser(UserPlatform userPlatform) {
        if (!platformService.existById(userPlatform.getPlatform().getId())) {
            this.platformService.savePlatform(userPlatform.getPlatform());
        }
        UserPlatform userPlatformSave = this.userPlatformRepository.save(userPlatform);
        activityService.createActivity(userPlatform.getUser(), null, userPlatform.getPlatform(), Category.ADD_PLATFORM);
        return userPlatformSave;
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
