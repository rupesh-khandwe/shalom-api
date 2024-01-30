package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.ShalomLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface ShalomLikeRepository extends JpaRepository<ShalomLike, Long> {

    ShalomLike findByShalomLikeId(Long id);

    @Transactional
    @Modifying
    @Query(value = "Update shalom.shalomlike\n" +
            "SET like_flag=:flag\n" +
            "where user_id=:userId and shalom_id=:shalomId",  nativeQuery = true)
    void updateLike(Long userId, Long shalomId, Boolean flag);

    @Query(value = "Select shalom_like_id \n" +
            "From shalom.shalomlike\n" +
            "where user_id=:userId and shalom_id=:shalomId",  nativeQuery = true)
    Long findByUserIdAndShalomId(Long userId, Long shalomId);
}
