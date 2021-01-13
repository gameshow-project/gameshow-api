package com.gameshow.api.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Long created_at;

    private Long category;

    private Long collection;

    private Long first_release_date;

    private String name;


}
