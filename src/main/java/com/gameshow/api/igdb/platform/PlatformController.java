package com.gameshow.api.igdb.platform;

import com.gameshow.api.shared.Platform;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@CrossOrigin
@Controller
@AllArgsConstructor
@RequestMapping("/api/platforms")
public class PlatformController {

    private final PlatformService platformService;

    @GetMapping("/platform_family/{id}")
    public ResponseEntity<?> findByPlatformFamily(@PathVariable("id") String platformFamily) {
        List<Platform> platforms = this.platformService.findPlatformsByPlatformFamily(platformFamily);
        return ResponseEntity.ok(platforms);
    }

}
