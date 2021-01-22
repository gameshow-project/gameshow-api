package com.gameshow.api.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "platform_logo")
public class PlatformLogo {

    @Id
    private Long id;
    private boolean alpha_channel;
    private boolean animated;
    private String image_id;
    private String url;

}
