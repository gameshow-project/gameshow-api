package com.gameshow.api.userPlatform;

import com.gameshow.api.security.SecurityService;
import com.gameshow.api.shared.Platform;
import com.gameshow.api.user.User;
import com.gameshow.api.user.UserNotFoundException;
import com.gameshow.api.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user_platform")
public class UserPlatformController {

    private final UserPlatformService userPlatformService;

    private final UserService userService;

    private final SecurityService securityService;

    @PostMapping("/add_platform")
    public ResponseEntity<UserPlatform> saveUserPlaform(@RequestBody Platform platform) {
        User user = securityService.getUser();
        UserPlatform userPlatform = new UserPlatform();
        userPlatform.setPlatform(platform);
        userPlatform.setUser(user);
        userPlatform.setQuantity(1);
        return ResponseEntity.ok(userPlatformService.addPlatformForUser(userPlatform));
    }

    @PostMapping("/add_quantity/platform/{platformId}")
    public ResponseEntity<UserPlatform> incrementQuantity(@PathVariable("platformId") Long platformId) {
        User user = securityService.getUser();
        return ResponseEntity.ok(userPlatformService.changeQuantity(user.getUid(), platformId, 1));
    }

    @PostMapping("/remove_quantity/platform/{platformId}")
    public ResponseEntity<UserPlatform> decreaseQuantity(@PathVariable("platformId") Long platformId) {
        User user = securityService.getUser();
        UserPlatform userPlatform = userPlatformService.changeQuantity(user.getUid(), platformId, -1);
        if (userPlatform == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userPlatform);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserPlatform>> getPlatformsUser(@PathVariable("id") String id) throws UserNotFoundException {
        User user = userService.findById(id);
        return ResponseEntity.ok(userPlatformService.getPlatformsUser(id));
    }

    @Transactional
    @DeleteMapping("/remove_platform/{platformId}")
    public ResponseEntity<?> deletePlatformForUser(@PathVariable("platformId") Long platformId) {
        User user = securityService.getUser();
        userPlatformService.deletePlatformForUser(platformId, user.getUid());
        return ResponseEntity.ok().build();
    }

}
