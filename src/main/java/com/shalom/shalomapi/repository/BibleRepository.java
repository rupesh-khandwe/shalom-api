package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Bible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface BibleRepository extends JpaRepository<Bible, Long> {

}
