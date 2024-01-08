package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Shalom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface ShalomRepository extends JpaRepository<Shalom, Long> {

    Shalom findByShalomId(Long id);

    @Query(value = "SELECT shl.shalom_id, shl.user_id, shl.shalom_flag, shl.shalom, shl.created_on, shl.updated_on, shl.image_url, shl.video_url, " +
            "COUNT(DISTINCT lk.user_id) AS \"like_count\","+
            "COUNT(DISTINCT cmt.user_id) AS \"comment_count\" "+
            "FROM shalom.shalom AS shl "+
            "LEFT OUTER JOIN shalom.shalomlike AS lk "+
            "ON shl.shalom_id = lk.shalom_id "+
            "LEFT OUTER JOIN shalom.shalomcomment AS cmt "+
            "ON shl.shalom_id = cmt.shalom_id "+
            "WHERE shl.user_id=:userId "+
            "GROUP BY shl.shalom_id",  nativeQuery = true)
    List<Shalom> findByUserId(Long userId);

    @Query(value = "SELECT shl.shalom_id, shl.user_id, shl.shalom_flag, shl.shalom, shl.created_on, shl.updated_on, shl.image_url, shl.video_url, " +
                    "COUNT(DISTINCT lk.user_id) AS \"like_count\","+
                    "COUNT(DISTINCT cmt.user_id) AS \"comment_count\" "+
                    "FROM shalom.shalom AS shl "+
                    "LEFT OUTER JOIN shalom.shalomlike AS lk "+
                    "ON shl.shalom_id = lk.shalom_id "+
                    "LEFT OUTER JOIN shalom.shalomcomment AS cmt "+
                    "ON shl.shalom_id = cmt.shalom_id "+
                    "GROUP BY shl.shalom_id",  nativeQuery = true)
    List<Shalom> findAllLikeComment();


//    @Query(value = "select * from shalom.church ch where lower(ch.church_name) LIKE %:searchKey%" +
//            "or lower(ch.addressline1) LIKE %:searchKey%" +
//            "or lower(ch.addressline2) LIKE %:searchKey%",  nativeQuery = true)
//    List<Church> findByChurchNameContainingOrAddressline1ContainingOrAddressline2ContainingCaseInsensitive(String searchKey);
}
