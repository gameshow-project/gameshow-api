package com.gameshow.api.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @OneToOne
    private PlatformFamily platform_family;

    private String slug;

    private Long updated_at;

}
