package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface RegionRepository extends JpaRepository<Region, Long> {

    public List<Region> findAllByCityId(Long cityId);
}
