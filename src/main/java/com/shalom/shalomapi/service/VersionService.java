package com.shalom.shalomapi.service;

import com.shalom.shalomapi.model.AppVersion;
import com.shalom.shalomapi.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VersionService {
    private final VersionRepository versionRepository;

    public String getLatestVersion() {
        AppVersion latestVersion = versionRepository.findAll().stream().findFirst().get();
        return latestVersion.getVersionNumber();
    }

    public void updateVersion(AppVersion version) {
        versionRepository.save(version);
    }
}