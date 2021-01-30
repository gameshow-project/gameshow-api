package com.gameshow.api.user.models;

import com.gameshow.api.user.Visibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class UserMinDto {

    private String uid;
    private String banner;
    private String lastname;
    private String firstname;
    private String username;
    private Visibility visibility;

}
