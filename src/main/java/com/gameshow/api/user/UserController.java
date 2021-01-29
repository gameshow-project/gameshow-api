package com.gameshow.api.user;

import com.gameshow.api.account.Account;
import com.gameshow.api.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final SecurityService securityService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) throws UserNotFoundException {
        User user = userService.findById(id);
        User userSecurity = securityService.getUser();
        if (user.getUid().equals(userSecurity.getUid())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        if (user.getVisibility() == Visibility.PRIVATE) {
            User userPrivate = User.builder()
                    .userGames(new ArrayList<>())
                    .userPlatforms(new ArrayList<>())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .visibility(user.getVisibility())
                    .username(user.getUsername()).build();

            return new ResponseEntity<>(userPrivate, HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<User> me() throws UserNotFoundException {
        User userSecurity = securityService.getUser();
        User user = userService.findById(userSecurity.getUid());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam("query") String query) {
        return ResponseEntity.ok(userService.searchUser(query));
    }

    @PostMapping("/set_banner/{imageId}")
    public ResponseEntity<User> setBanner(@PathVariable("imageId") String imageId) throws UserNotFoundException {
        User userSecurity = securityService.getUser();
        User user = userService.findById(userSecurity.getUid());
        return ResponseEntity.ok(userService.setBanner(imageId, user));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User userSecurity = securityService.getUser();
        if (user.getUid().equals(userSecurity.getUid())) {
            return ResponseEntity.ok(userService.saveUser(user));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/set_account")
    ResponseEntity<User> setAccount(@RequestBody Account account) throws UserNotFoundException {
        User userSecurity = securityService.getUser();
        User user = userService.findById(userSecurity.getUid());
        return ResponseEntity.ok(this.userService.saveAccount(account, user));
    }

}
