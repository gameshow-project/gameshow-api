package com.gameshow.api.game;

import com.gameshow.api.shared.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    boolean existsById(Long id);

    Optional<Game> findById(Long id);

}
