package com.gameshow.api.userPlatform;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserPlatformRepository extends JpaRepository<UserPlatform, UserPlatformId> {

    List<UserPlatform> findAllByUserUid(String id);

    @Override
    void deleteById(UserPlatformId userPlatformId);

    @Override
    Optional<UserPlatform> findById(UserPlatformId userPlatformId);
}
