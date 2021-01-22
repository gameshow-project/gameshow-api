package com.gameshow.api.activity;

import com.gameshow.api.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Activity>> getActivitiesByUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(activityService.getActivitiesByUser(userId));
    }

    @PostMapping("/like/{activityId}")
    public ResponseEntity<Activity> likeActivitiy(@PathVariable("activityId") Long activityId, @RequestBody User user) throws ActivityNotFoundException {
        return ResponseEntity.ok(activityService.likeActivity(activityId, user));
    }

    @PostMapping("/dislike/{activityId}/{userId}")
    public ResponseEntity<Activity> dislikeActivity(@PathVariable("activityId") Long activityId, @PathVariable("userId") Long userId) throws ActivityNotFoundException {
        return ResponseEntity.ok(activityService.dislikeActivity(activityId, userId));
    }


}
