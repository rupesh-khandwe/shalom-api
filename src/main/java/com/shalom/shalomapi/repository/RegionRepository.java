package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query(value = "Select * \n" +
            "From shalom.region rg\n" +
            "where rg.city_id=:cityId\n" +
            "ORDER BY rg.region_name ASC\n" +
            "limit 10",  nativeQuery = true)
    public List<Region> findAllByCityId(Long cityId);

    @Query(value = "Select * \n" +
            "From shalom.region rg\n" +
            "where rg.city_id=:cityId\n" +
            "and (LOWER(rg.region_name) like :searchKey% OR rg.pincode LIKE :searchKey%) \n" +
            "ORDER BY rg.region_name ASC\n" +
            "limit 10",  nativeQuery = true)
    public List<Region> findAllByCityIdAndRegionNameOrPincode(Long cityId, String searchKey);
}
