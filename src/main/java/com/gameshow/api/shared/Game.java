package com.gameshow.api.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "game")
public class Game {

    @Id
    private Long id;

    @OneToOne
    private Cover cover;

    @ElementCollection
    private List<Long> game_modes;

    @ElementCollection
    private List<Long> genres;

    @ElementCollection
    private List<Long> player_perspectives;

    private Double rating;

    @OneToMany
    private List<Cover> screenshots;

    @ManyToMany
    private List<Platform> platforms;

    @OneToMany
    private List<Video> videos;

    private Long created_at;

    private Long category;

    private Long first_release_date;

    private String name;

    @ManyToMany
    private List<OtherGame> similar_games;

}
