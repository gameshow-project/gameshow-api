package com.gameshow.api.comment;

import com.gameshow.api.shared.Game;
import com.gameshow.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comment parent;

    @ManyToOne
    private Game game;

    @ManyToMany
    private Set<User> like;

    @ManyToOne
    private User author;

    private Long date;

}
