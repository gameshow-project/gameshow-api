package com.gameshow.api.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    /**
     * Retrieve a auth by email
     * @param email
     * @return user
     */
    public Auth getAuthByEmail(String email) throws AuthNotFoundException {
        return authRepository.findByEmail(email).orElseThrow(() -> new AuthNotFoundException(email));
    }

    /**
     * Save a auth
     * @param auth
     */
    public Auth saveAuth(Auth auth) {
        return authRepository.save(auth);
    }

    /**
     * Indicates if the email of auth already exists
     * @param email
     * @return boolean
     */
    public boolean emailExists(String email) {
        return authRepository.existsByEmail(email);
    }

}
