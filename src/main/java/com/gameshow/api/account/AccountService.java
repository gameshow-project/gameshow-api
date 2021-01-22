package com.gameshow.api.account;

import com.gameshow.api.auth.AuthNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account getAccountByUserId(Long userId) throws AccountNotFoundException {
        return accountRepository.findByUserId(userId).orElseThrow(() -> new AccountNotFoundException(userId));
    }

    public Account saveAccount(Account account) {
        return this.accountRepository.save(account);
    }

}
