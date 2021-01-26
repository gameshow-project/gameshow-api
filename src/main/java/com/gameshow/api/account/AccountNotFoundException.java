package com.gameshow.api.account;

public class AccountNotFoundException extends Exception {

    AccountNotFoundException(String userId) {
        super("Account with userId " + userId + " not found");
    }
}
