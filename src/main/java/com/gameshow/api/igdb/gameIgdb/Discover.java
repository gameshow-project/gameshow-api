package com.gameshow.api.igdb.gameIgdb;

import com.gameshow.api.shared.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Discover {

    private List<Game> recentlyRelease;

}
