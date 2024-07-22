package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface LanguageRepository extends JpaRepository<Language, Long> {


}
