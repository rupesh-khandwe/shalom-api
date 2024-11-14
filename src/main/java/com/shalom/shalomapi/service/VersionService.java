package com.shalom.shalomapi.service;

import com.shalom.shalomapi.model.Version;
import com.shalom.shalomapi.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VersionService {
    private final VersionRepository versionRepository;

    public String getLatestVersion() {
        Version latestVersion = versionRepository.findLatestVersion();
        return latestVersion.getVersionNumber();
    }
    public void updateVersion(Version version) {
        versionRepository.save(version);
    }
}