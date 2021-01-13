package com.gameshow.api.user;

import com.gameshow.api.auth.AuthService;
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

    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
