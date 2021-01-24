package com.gameshow.api.twitch.twitchStream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TwitchStream {

    private Long _id;
    private String game;
    private String broadcast_platform;
    private int viewers;
    private int average_fps;
    private int delay;
    private String created_at;
    private String stream_type;
    private TwitchChannel channel;
    private TwitchPreview preview;
}
