package com.gameshow.api.user;

import com.gameshow.api.account.Account;
import com.gameshow.api.account.AccountService;
import com.gameshow.api.auth.AuthService;
import com.gameshow.api.config.security.jwt.TokenProvider;
import com.gameshow.api.platform.PlatformService;
import com.gameshow.api.shared.Platform;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final AccountService accountService;

    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User setBanner(String imageId, User user) {
        user.setBanner(imageId);
        return this.userRepository.save(user);
    }

    public User saveAccount(Account account, User user) {
        accountService.saveAccount(account);
        user.setAccount(account);
        return this.saveUser(user);
    }

}
