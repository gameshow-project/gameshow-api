package com.gameshow.api.userGame;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserGameRepository extends JpaRepository<UserGame, UserGameId> {

    List<UserGame> findAllByUserId(Long id);

    int countByGame_Id(Long gameId);

    @Override
    boolean existsById(UserGameId userGameId);

    @Override
    Optional<UserGame> findById(UserGameId userGameId);
}
