package com.gameshow.api.igdb.platformIgdb;

import com.gameshow.api.shared.Platform;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@Controller
@AllArgsConstructor
@RequestMapping("/api/platform_igdb")
public class PlatformIgdbController {

    private final PlatformIgdbService platformIgdbService;

    @GetMapping("/platform_family/{id}")
    public ResponseEntity<?> findByPlatformFamily(@PathVariable("id") String platformFamily) {
        List<Platform> platforms = this.platformIgdbService.findPlatformsByPlatformFamily(platformFamily);
        return ResponseEntity.ok(platforms);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Platform>> researchPlatform(@RequestParam("q") String research) {
        return ResponseEntity.ok(platformIgdbService.researchPlatform(research));
    }

}
