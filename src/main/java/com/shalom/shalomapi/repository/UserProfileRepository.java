package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.IEditUserProfile;
import com.shalom.shalomapi.model.IUser;
import com.shalom.shalomapi.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    public UserProfile findByUserNameAndPassword(String userName, String password);

    public UserProfile findByUserName(String userName);

    @Query(value = "SELECT up.email as userEmail, up.first_name as userFirstName, up.middle_name as userMiddleName, up.last_name as userLastName, up.phone1 as userPhone1, up.phone2 as userPhone2,\n" +
            "up.address_line1 as userAddressLine1, up.address_line2 as userAddressLine2, up.user_name as userName,\n" +
            "rg.region_id as regionId, rg.region_name as userRegionName,ct.city_id as cityId, ct.city_name as userCityName, st.state_id as stateId, st.state_name as userStateName, cnt.country_id as countryId, cnt.country_name as userCountryName\n" +
            "FROM shalom.userprofile up\n" +
            "JOIN shalom.region rg ON up.region_id=rg.region_id\n"+
            "JOIN shalom.city ct ON ct.city_id=up.city_id\n" +
            "JOIN shalom.state st ON st.state_id=up.state_id\n" +
            "JOIN shalom.country cnt ON cnt.country_id=up.country_id\n" +
            "WHERE up.user_id=:userId", nativeQuery = true)
    public IEditUserProfile findUserProfileByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "Update shalom.userfollow\n" +
            "SET follow_flag=:flag\n" +
            "where user_id=:userId and follow_id=:followId",  nativeQuery = true)
    public void updateFollowFlag(Long userId, Long followId, Boolean flag);

    @Transactional
    @Modifying
    @Query(value = "Update shalom.userprofile\n" +
            "SET email=:email, first_name=:firstName, middle_name=:middleName, last_name=:lastName,\n" +
            "phone1=:phone1, phone2=:phone2, address_line1=:addressLine1, address_line2=:addressLine2,\n" +
            "state_id=:stateId, city_id=:cityId, region_id=:regionId\n" +
            "where user_id=:userId",  nativeQuery = true)
    public void saveOrUpdate(Long userId, String email, String firstName, String middleName, String lastName, String phone1, String phone2, String addressLine1, String addressLine2, Long stateId, Long cityId, Long regionId);

    @Query(value="SELECT up.user_id as userId, (coalesce(up.first_name, '')|| ' ' || coalesce(up.last_name, '')) as userName \n" +
            "FROM shalom.userprofile up\n" +
            "WHERE up.user_id NOT IN (SELECT uf.follow_id FROM shalom.userfollow uf WHERE uf.user_id=:userId AND uf.follow_flag=:followFlag)\n" +
            "AND up.user_id!=:userId", nativeQuery = true)
    public List<IUser> getUsers(Long userId, Boolean followFlag);
}
