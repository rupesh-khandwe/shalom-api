package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByEventId(Long id);

    @Query(value = "select * from shalom.event ev " +
            " WHERE ev.user_id IN(SELECT uf.follow_id FROM shalom.userfollow uf WHERE uf.user_id=:userId AND uf.follow_flag=true) OR ev.user_id=:userId " +
            "ORDER BY ev.created_on DESC",  nativeQuery = true)
    List<Event> findByUserId(Long userId);
}
