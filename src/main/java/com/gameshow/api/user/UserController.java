package com.gameshow.api.user;

import com.gameshow.api.account.Account;
import com.gameshow.api.auth.AuthNotFoundException;
import com.gameshow.api.auth.AuthService;
import com.gameshow.api.config.security.jwt.JWTFilter;
import com.gameshow.api.config.security.jwt.TokenProvider;
import com.gameshow.api.shared.Platform;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final AuthService authService;

    private final TokenProvider tokenProvider;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/set_banner/{imageId}")
    public ResponseEntity<User> setBanner(HttpServletRequest request, @PathVariable("imageId") String imageId) throws AuthNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        return ResponseEntity.ok(userService.setBanner(imageId, user));
    }


    @PostMapping("/set_account")
    ResponseEntity<User> setAccount(HttpServletRequest request, @RequestBody Account account) throws AuthNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        User user = authService.getUserByEmail(requestEmail);
        return ResponseEntity.ok(this.userService.saveAccount(account, user));
    }

}
