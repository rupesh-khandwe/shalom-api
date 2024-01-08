package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    public UserProfile findByUserNameAndPassword(String userName, String password);

    public UserProfile findByUserName(String userName);
}
