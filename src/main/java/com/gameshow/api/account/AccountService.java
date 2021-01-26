package com.gameshow.api.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account getAccountByUserId(String userId) throws AccountNotFoundException {
        return accountRepository.findByUserUid(userId).orElseThrow(() -> new AccountNotFoundException(userId));
    }

    public Account saveAccount(Account account) {
        return this.accountRepository.save(account);
    }

}
