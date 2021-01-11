package com.gameshow.api.igdb;

import lombok.Data;

@Data
public class IgdbToken {

    private String access_token;
    private String expires_in;
    private String token_type;

}
