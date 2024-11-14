package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version, Long> {
    Version findLatestVersion();
}