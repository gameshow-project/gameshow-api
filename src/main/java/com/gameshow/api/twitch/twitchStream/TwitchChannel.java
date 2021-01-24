package com.gameshow.api.twitch.twitchStream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TwitchChannel {

    private boolean mature;
    private String status;
    private String broadcaster_language;
    private String display_name;
    private String game;
    private String language;
    private Long _id;
    private String name;
    private String created_at;
    private String updated_at;
    private boolean partner;
    private String logo;
    private String video_banner;
    private String profile_banner;
    private String profile_banner_background_color;
    private String url;
    private int views;
    private int followers;
    private String description;

}
