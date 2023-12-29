package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface CountryRepository extends JpaRepository<Country, Long> {

    public List<Country> findAllByOrderByCountryNameAsc();
}
