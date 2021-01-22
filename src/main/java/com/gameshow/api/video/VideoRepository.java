package com.gameshow.api.video;

import com.gameshow.api.shared.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
