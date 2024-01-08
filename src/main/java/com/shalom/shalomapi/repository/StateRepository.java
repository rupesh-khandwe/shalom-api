package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface StateRepository extends JpaRepository<State, Long> {

    public List<State> findAllByCountryId(Long countryId);
}
