package com.gameshow.api.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gameshow.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "platform")
public class Platform {

    @Id
    private Long id;

    private String abbreviation;

    private Long category;

    private Long created_at;

    private Long generation;

    private String name;

    @ManyToOne
    @JoinColumn(name = "platform_family")
    private PlatformFamily platform_family;

    private String slug;

    private Long updated_at;

    @OneToOne
    @JoinColumn(name = "platform_logo")
    private PlatformLogo platform_logo;

}
