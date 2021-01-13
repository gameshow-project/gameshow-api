package com.gameshow.api.user;

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

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
