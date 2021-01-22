package com.gameshow.api.cover;

import com.gameshow.api.shared.Cover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverRepository extends JpaRepository<Cover, Long> {
}
