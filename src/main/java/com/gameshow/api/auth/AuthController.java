package com.gameshow.api.auth;

import com.gameshow.api.config.security.jwt.JWTFilter;
import com.gameshow.api.config.security.jwt.TokenProvider;
import com.gameshow.api.user.User;
import com.gameshow.api.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.desktop.UserSessionEvent;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    /**
     * Check if user is authenticate
     */
    @GetMapping("/authenticate")
    public void authenticate() {
        // Empty
    }

    /**
     * Authenticate a user
     * @param signin
     * @return token
     */
    @PostMapping(value = "/signin", consumes = { "application/json" })
    public ResponseEntity<String> signin(@Valid @RequestBody Signin signin) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signin.getEmail(), signin.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
            return new ResponseEntity<>(tokenProvider.createToken(signin.getEmail()), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Recover a user authentificated
     * @return user
     */
    @GetMapping(value = "/me")
    public ResponseEntity<?> recoverUserAuthentificated(HttpServletRequest request) throws AuthNotFoundException {
        String requestEmail = tokenProvider.getUsername(JWTFilter.resolveToken(request));
        Auth auth = authService.getAuthByEmail(requestEmail);
        return ResponseEntity.ok(auth.getUser());
    }

    /**
     * Register a new user
     * @param auth
     * @return token
     */
    @PostMapping(value = "/signup", consumes = { "application/json" })
    public ResponseEntity<String> signup(@Valid @RequestBody Auth auth) {
        if (authService.emailExists(auth.getEmail())) {
            return new ResponseEntity<>("Email already exist", HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(auth.getUser());
        auth.setPassword(passwordEncoder.encode(auth.getPassword()));
        authService.saveAuth(auth);
        return new ResponseEntity<>(tokenProvider.createToken(auth.getEmail()), HttpStatus.CREATED);
    }

    @ExceptionHandler(AuthNotFoundException.class)
    public final ResponseEntity<String> handleAuthNotFoundException(AuthNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
