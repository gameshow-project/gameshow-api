package com.gameshow.api.auth;

import com.gameshow.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findByEmail(String email);

    boolean existsByEmail(String email);

}
