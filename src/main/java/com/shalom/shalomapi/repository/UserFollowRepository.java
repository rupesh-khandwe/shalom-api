package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.IUserFollow;
import com.shalom.shalomapi.model.IUserProfile;
import com.shalom.shalomapi.model.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {

    @Transactional
    @Modifying
    @Query(value = "Update shalom.userfollow\n" +
            "SET follow_flag=:flag\n" +
            "where user_id=:userId and follow_id=:followId",  nativeQuery = true)
    void updateFollowFlag(Long userId, Long followId, Boolean flag);

    public UserFollow findByUserIdAndFollowId(Long userId, Long followId);

    @Query(value = "Select uf.follow_seq_id as followSeqId, uf.user_id as userId, uf.follow_id as followId, uf.follow_flag as followFlag, up.first_name as firstName, up.last_name as lastName\n" +
            "From shalom.userfollow uf\n" +
            "JOIN shalom.userprofile up ON up.user_id = uf.follow_id\n" +
            "where uf.user_id=:userId\n" +
            "AND uf.follow_flag=:flag\n" +
            "ORDER BY uf.created_on DESC",  nativeQuery = true)
    List<IUserFollow> findByUserId(Long userId, Boolean flag);

    @Query(value = "Select uf.follow_seq_id as followSeqId, uf.user_id as userId, uf.follow_id as followId, uf.follow_flag as followFlag, up.first_name as firstName, up.last_name as lastName\n" +
            "From shalom.userfollow uf\n" +
            "JOIN shalom.userprofile up ON up.user_id = uf.user_id\n" +
            "where uf.follow_id=:followId\n" +
            "AND uf.follow_flag=:flag\n" +
            "ORDER BY uf.created_on DESC",  nativeQuery = true)
    List<IUserFollow> findByFollowId(Long followId, Boolean flag);

    @Query(value = "Select (\n" +
            "Select count(uf.follow_id) from shalom.userfollow uf\n" +
            "where uf.user_id=:userId and uf.follow_flag=true\n" +
            ") AS followingsCount,\n" +
            "(select count(uf.user_id) from shalom.userfollow uf\n"+
            "where uf.follow_id=:userId and uf.follow_flag=true\n" +
            ") AS followersCount,\n" +
            "(SELECT count(*) FROM shalom.shalom sh\n" +
            "where sh.user_id=:userId\n" +
            ") AS shalomCount,\n" +
            "(SELECT ct.city_name FROM shalom.userprofile up\n" +
            "JOIN shalom.city ct ON ct.city_id = up.city_id\n" +
            "WHERE up.user_id=:userId) AS userCity,\n" +
            "(SELECT st.state_name  FROM shalom.userprofile up\n" +
            "JOIN shalom.state st ON st.state_id = up.state_id\n" +
            "WHERE up.user_id=:userId) AS userState,\n" +
            "(SELECT cnt.country_name  FROM shalom.userprofile up\n" +
            "JOIN shalom.country cnt ON cnt.country_id = up.country_id\n" +
            "WHERE up.user_id=:userId) AS userCountry\n", nativeQuery = true)
    IUserProfile findCountByUserId(Long userId);
}
