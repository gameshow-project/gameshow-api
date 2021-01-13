package com.gameshow.api.platformFamily;

import com.gameshow.api.shared.PlatformFamily;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformFamilyRepository extends JpaRepository<PlatformFamily, Long> {

    public boolean existsById(Long id);

}
