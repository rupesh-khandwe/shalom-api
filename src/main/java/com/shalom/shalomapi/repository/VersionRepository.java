package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<AppVersion, Long> {
}