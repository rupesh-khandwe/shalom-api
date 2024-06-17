package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Church;
import com.shalom.shalomapi.model.IChurch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface ChurchRepository extends JpaRepository<Church, Long> {

    Church findByChurchId(Long id);


    @Query(value = "select ch.church_id as churchId, ch.user_id as userId, ch.church_name as churchName, ch.church_website_url as churchWebsiteUrl, ch.phone1 as phone1, ch.phone2 as phone2, ch.addressline1 as addressLine1, ch.addressline2 as addressLine2, ch.created_on as createdOn, ch.updated_on as updatedOn,  ch.created_by as createdBy, " +
            "rg.region_id as regionId, rg.region_name as userRegionName,ct.city_id as cityId, ct.city_name as userCityName, st.state_id as stateId, st.state_name as userStateName, cnt.country_id as countryId, cnt.country_name as userCountryName,\n" +
            "up.image_url AS profileImageUrl "+
            "from shalom.church ch " +
            "JOIN shalom.userprofile up ON up.user_id = ch.user_id " +
            "JOIN shalom.region rg ON ch.region_id=rg.region_id\n"+
            "JOIN shalom.city ct ON ct.city_id=ch.city_id\n" +
            "JOIN shalom.state st ON st.state_id=ch.state_id\n" +
            "JOIN shalom.country cnt ON cnt.country_id=ch.country_id\n" +
//            "where lower(ch.church_name) LIKE %:searchKey%" +
//            "or lower(ch.addressline1) LIKE %:searchKey%" +
//            "or lower(ch.addressline2) LIKE %:searchKey%" +
//            "or ch.city_id=:cityId" +
            " ORDER BY ch.created_on DESC", nativeQuery = true)
    List<IChurch> findByChurchNameContainingOrAddressline1ContainingOrAddressline2ContainingCaseInsensitive();
}
