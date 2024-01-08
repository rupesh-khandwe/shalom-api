package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface CityRepository extends JpaRepository<City, Long> {

    public List<City> findAllByStateIdAndCountryId(Long stateId, Long countryId);
}
