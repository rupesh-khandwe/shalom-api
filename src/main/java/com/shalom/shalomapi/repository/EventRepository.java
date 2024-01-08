package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByEventId(Long id);

    List<Event> findByUserId(Long userId);


//    @Query(value = "select * from shalom.church ch where lower(ch.church_name) LIKE %:searchKey%" +
//            "or lower(ch.addressline1) LIKE %:searchKey%" +
//            "or lower(ch.addressline2) LIKE %:searchKey%",  nativeQuery = true)
//    List<Church> findByChurchNameContainingOrAddressline1ContainingOrAddressline2ContainingCaseInsensitive(String searchKey);
}
