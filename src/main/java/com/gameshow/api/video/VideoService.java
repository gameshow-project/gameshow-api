package com.gameshow.api.video;

import com.gameshow.api.shared.Video;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

}
