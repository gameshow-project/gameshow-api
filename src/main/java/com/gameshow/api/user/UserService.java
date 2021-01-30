package com.gameshow.api.user;

import com.gameshow.api.account.Account;
import com.gameshow.api.account.AccountService;
import com.gameshow.api.user.converters.UserMinConverter;
import com.gameshow.api.user.models.UserMinDto;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final UserMinConverter userMinConverter;

    public User findById(String id) throws UserNotFoundException {
        return userRepository.findByUid(id).orElseThrow(() -> new UserNotFoundException(id));
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

    public List<UserMinDto> searchUser(String query) {
        UserSpecificationBuilder builder = new UserSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(query + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<User> spec = builder.build();
        return userMinConverter.listEntityToDto(userRepository.findAll(spec));
    }

}
