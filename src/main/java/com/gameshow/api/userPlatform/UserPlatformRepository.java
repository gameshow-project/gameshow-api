package com.gameshow.api.userPlatform;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserPlatformRepository extends JpaRepository<UserPlatform, UserPlatformId> {

    List<UserPlatform> findAllByUserId(Long id);

    void deleteByPlatformIdAndUserId(Long platformId, Long userId);

    @Override
    Optional<UserPlatform> findById(UserPlatformId userPlatformId);
}
