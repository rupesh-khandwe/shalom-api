package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.model.Version;
import com.shalom.shalomapi.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/version/v1")
@RequiredArgsConstructor
public class VersionController {
    private final VersionService versionService;

    @GetMapping
    public ResponseEntity<String> getLatestVersion() {
        String latestVersion = versionService.getLatestVersion();
        return ResponseEntity.ok(latestVersion);
    }

    @PostMapping("/update-version")
    public ResponseEntity<String> updateVersion(@RequestBody Version version) {
        versionService.updateVersion(version);
        return ResponseEntity.ok("Version updated successfully");
    }
}