package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.IShalomComment;
import com.shalom.shalomapi.model.ShalomComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface ShalomCommentRepository extends JpaRepository<ShalomComment, Long> {

    @Transactional
    @Modifying
    @Query(value = "Update shalom.shalomlike\n" +
            "SET like_flag=:flag\n" +
            "where user_id=:userId and shalom_id=:shalomId",  nativeQuery = true)
    void updateComment(Long userId, Long shalomId, Boolean flag);

    @Query(value = "Select sc.shalom_comment as shalomComment, sc.created_on as createdOn, sc.user_name as userName,up.image_url as imageUrl \n" +
            "From shalom.shalomcomment sc\n" +
            "JOIN shalom.userprofile up ON up.user_id = sc.user_id\n" +
            "where sc.shalom_id=:shalomId\n" +
            "ORDER BY sc.created_on DESC",  nativeQuery = true)
    List<IShalomComment> findByShalomId(Long shalomId);
}
