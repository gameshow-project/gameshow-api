package com.gameshow.api.twitch.twitchStream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TwitchStreams {

    private List<TwitchStream> streams;

}
