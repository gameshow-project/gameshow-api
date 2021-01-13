package com.gameshow.api.userPlatform;

import com.gameshow.api.auth.AuthNotFoundException;
import com.gameshow.api.auth.AuthService;
import com.gameshow.api.config.security.jwt.JWTFilter;
import com.gameshow.api.config.security.jwt.TokenProvider;
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

    private final AuthService authService;

    private final TokenProvider tokenProvider;

    @PostMapping("/add_platform")
    public ResponseEntity<UserPlatform> saveUserPlaform(HttpServletRequest request, @RequestBody Platform platform) throws UserNotFoundException, AuthNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        UserPlatform userPlatform = new UserPlatform();
        userPlatform.setPlatform(platform);
        userPlatform.setUser(user);
        userPlatform.setQuantity(1);
        return ResponseEntity.ok(userPlatformService.addPlatformForUser(userPlatform));
    }

    @PostMapping("/add_quantity/platform/{platformId}")
    public ResponseEntity<UserPlatform> incrementQuantity(HttpServletRequest request, @PathVariable("platformId") Long platformId) throws AuthNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        return ResponseEntity.ok(userPlatformService.changeQuantity(user.getId(), platformId, 1));
    }

    @PostMapping("/remove_quantity/platform/{platformId}")
    public ResponseEntity<UserPlatform> decreaseQuantity(HttpServletRequest request, @PathVariable("platformId") Long platformId) throws AuthNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        UserPlatform userPlatform = userPlatformService.changeQuantity(user.getId(), platformId, -1);
        if (userPlatform == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userPlatform);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserPlatform>> getPlatformsUser(@PathVariable("id") Long id) throws UserNotFoundException {
        User user = userService.findById(id);
        return ResponseEntity.ok(userPlatformService.getPlatformsUser(id));
    }

    @Transactional
    @DeleteMapping("/remove_platform/{platformId}")
    public ResponseEntity<?> deletePlatformForUser(HttpServletRequest request, @PathVariable("platformId") Long platformId) throws AuthNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        userPlatformService.deletePlatformForUser(platformId, user.getId());
        return ResponseEntity.ok().build();
    }

}
