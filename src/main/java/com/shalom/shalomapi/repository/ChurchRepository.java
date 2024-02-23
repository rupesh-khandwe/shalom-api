package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Church;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface ChurchRepository extends JpaRepository<Church, Long> {

    Church findByChurchId(Long id);


    @Query(value = "select * from shalom.church ch where lower(ch.church_name) LIKE %:searchKey%" +
            "or lower(ch.addressline1) LIKE %:searchKey%" +
            "or lower(ch.addressline2) LIKE %:searchKey%" +
            "or ch.city_id LIKE '228'" +
            " ORDER BY ch.created_on DESC", nativeQuery = true)
    List<Church> findByChurchNameContainingOrAddressline1ContainingOrAddressline2ContainingCaseInsensitive(String searchKey);
}
