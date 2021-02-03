package com.gameshow.api.user;

import com.gameshow.api.account.Account;
import com.gameshow.api.security.SecurityService;
import com.gameshow.api.user.converters.UserDetailsConverter;
import com.gameshow.api.user.converters.UserMinConverter;
import com.gameshow.api.user.models.UserDetailsDto;
import com.gameshow.api.user.models.UserMinDto;
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
    private final UserDetailsConverter userDetailsConverter;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> getUserById(@PathVariable("id") String id) throws UserNotFoundException {
        User user = userService.findById(id);
        User userSecurity = securityService.getUser();
        if (user.getUid().equals(userSecurity.getUid())) {
            return new ResponseEntity<>(userDetailsConverter.entityToDto(user), HttpStatus.OK);
        }
        if (user.getVisibility() == Visibility.PRIVATE) {
            UserDetailsDto userPrivate = UserDetailsDto.builder()
                    .userGames(new ArrayList<>())
                    .userPlatforms(new ArrayList<>())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .visibility(user.getVisibility())
                    .username(user.getUsername())
                    .uid(user.getUid()).build()
                    ;

            return new ResponseEntity<>(userPrivate, HttpStatus.OK);
        }
        return new ResponseEntity<>(userDetailsConverter.entityToDto(user), HttpStatus.OK);
    }

    @PostMapping("/bio")
    public ResponseEntity<User> setBio(@RequestBody String bio) throws UserNotFoundException {
        User userSecurity = securityService.getUser();
        User user = userService.findById(userSecurity.getUid());
        user.setBio(bio);
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailsDto> me() throws UserNotFoundException {
        User userSecurity = securityService.getUser();
        User user = userService.findById(userSecurity.getUid());
        return ResponseEntity.ok(userDetailsConverter.entityToDto(user));
    }

    @PostMapping("/visibility")
    public ResponseEntity<User> changeVisibility(@RequestParam("value") String value) throws UserNotFoundException {
        User userSecurity = securityService.getUser();
        User user = userService.findById(userSecurity.getUid());
        user.setVisibility(Visibility.valueOf(value));
        return ResponseEntity.ok(userService.saveUser(user));

    }

    @GetMapping("/search")
    public ResponseEntity<List<UserMinDto>> searchUser(@RequestParam("query") String query) {
        return ResponseEntity.ok(userService.searchUser(query));
    }

    @PostMapping("/set_banner/{imageId}")
    public ResponseEntity<User> setBanner(@PathVariable("imageId") String imageId) throws UserNotFoundException {
        User userSecurity = securityService.getUser();
        User user = userService.findById(userSecurity.getUid());
        return ResponseEntity.ok(userService.setBanner(imageId, user));
    }

    @PostMapping
    public ResponseEntity<UserDetailsDto> saveUser(@RequestBody User user) {
        User userSecurity = securityService.getUser();
        if (user.getUid().equals(userSecurity.getUid())) {
            return ResponseEntity.ok(userDetailsConverter.entityToDto(userService.saveUser(user)));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/set_account")
    ResponseEntity<User> setAccount(@RequestBody Account account) throws UserNotFoundException {
        User userSecurity = securityService.getUser();
        User user = userService.findById(userSecurity.getUid());
        if (!account.getId().equals(user.getAccount().getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(this.userService.saveAccount(account, user));
    }

}
