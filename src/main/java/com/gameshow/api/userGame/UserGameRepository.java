package com.gameshow.api.userGame;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGameRepository extends JpaRepository<UserGame, UserGameId> {

    List<UserGame> findAllByUserId(Long id);

    int countByGame_Id(Long gameId);

}
