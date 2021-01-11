package com.gameshow.api.config.security;

import com.gameshow.api.auth.Auth;
import com.gameshow.api.auth.AuthNotFoundException;
import com.gameshow.api.auth.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AppUserDetailService implements UserDetailsService {

    private final AuthService authService;

    public AppUserDetailService(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Auth auth;
        try {
            auth = this.authService.getAuthByEmail(email);
        } catch (AuthNotFoundException e) {
            throw new UsernameNotFoundException("Auth with email '" + email + "' not found");
        }

        return org.springframework.security.core.userdetails.User.withUsername(email)
                .password(auth.getPassword()).authorities(Collections.emptyList())
                .accountExpired(false).accountLocked(false).credentialsExpired(false)
                .disabled(false).build();
    }
}
