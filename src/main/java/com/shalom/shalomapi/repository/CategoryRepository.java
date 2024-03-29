package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface CategoryRepository extends JpaRepository<Category, Long> {


}
