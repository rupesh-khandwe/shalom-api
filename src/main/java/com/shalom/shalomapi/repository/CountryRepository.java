package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "SELECT * FROM shalom.country c\n" +
            "where c.flag=true\n" +
            "ORDER BY c.country_name ASC",  nativeQuery = true)
    public List<Country> findAllByOrderByCountryNameAsc();
}
