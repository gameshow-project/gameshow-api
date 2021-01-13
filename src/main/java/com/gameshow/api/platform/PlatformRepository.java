package com.gameshow.api.platform;

import com.gameshow.api.shared.Platform;
import com.gameshow.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    boolean existsById(Long id);

}
