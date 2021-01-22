package com.gameshow.api.activity;

import com.gameshow.api.shared.Game;
import com.gameshow.api.shared.Platform;
import com.gameshow.api.user.User;
import com.gameshow.api.user.UserNotFoundException;
import com.gameshow.api.userGame.UserGame;
import com.gameshow.api.userPlatform.UserPlatform;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public Activity getById(Long activityId) throws ActivityNotFoundException {
        return activityRepository.findById(activityId).orElseThrow(() -> new ActivityNotFoundException(activityId));
    }

    public List<Activity> getActivitiesByUser(Long userId) {
        return activityRepository.findAllByUserIdOrderByDateDesc(userId);
    }

    public Activity createActivity(User user, Game game, Platform platform, Category category) {
        LocalDateTime now = LocalDateTime.now();
        Activity activity = Activity.builder()
                .category(category)
                .game(game)
                .platform(platform)
                .user(user)
                .date(now.toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(now)))
                .build();
        return activityRepository.save(activity);
    }

    public Activity likeActivity(Long activityId, User user) throws ActivityNotFoundException {
        Activity activity = this.getById(activityId);
        activity.getUsers().add(user);
        return activityRepository.save(activity);
    }

    public Activity dislikeActivity(Long activityId, Long userId) throws ActivityNotFoundException {
        Activity activity = this.getById(activityId);
        activity.getUsers().removeIf(user -> user.getId().equals(userId));
        return activityRepository.save(activity);
    }
}
