package com.gameshow.api.platformFamily;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/platform_family")
public class PlatformFamilyController {

    private final PlatformFamilyService platformFamilyService;

}
