package com.shalom.shalomapi.repository;

import com.shalom.shalomapi.model.Event;
import com.shalom.shalomapi.model.IEvent;
import com.shalom.shalomapi.model.IEventNotify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "select ev.event_id as eventId, ev.user_id as userId, ev.category_id as categoryId, cat.category_name as categoryName, ev.title as title, ev.description as description, ev.event_date as eventDate, " +
            "ev.event_time as eventTime, ev.created_by as createdBy, ev.created_on as createdOn, ev.updated_on as updatedOn, ev.phone1 as phone1, ev.phone2 as phone2, ev.addressline1 as addressLine1, ev.addressline2 as addressLine2, ev.image_url as eventImageUrl," +
            "rg.region_id as regionId, rg.region_name as userRegionName, ct.city_id as cityId, ct.city_name as userCityName, st.state_id as stateId, st.state_name as userStateName, cnt.country_id as countryId, cnt.country_name as userCountryName,\n" +
            "up.image_url AS profileImageUrl "+
            "from shalom.event ev " +
            "JOIN shalom.category cat ON ev.category_id = cat.category_id " +
            "JOIN shalom.userprofile up ON up.user_id = ev.user_id " +
            "JOIN shalom.region rg ON ev.region_id=rg.region_id\n"+
            "JOIN shalom.city ct ON ct.city_id=ev.city_id\n" +
            "JOIN shalom.state st ON st.state_id=ev.state_id\n" +
            "JOIN shalom.country cnt ON cnt.country_id=ev.country_id\n" +
            " WHERE ev.event_id =:eventId ",  nativeQuery = true)
    IEvent findByEventId(Long eventId);

    @Query(value = "select ev.event_id as eventId, ev.user_id as userId, ev.category_id as categoryId, cat.category_name as categoryName, ev.title as title, ev.description as description, ev.event_date as eventDate, " +
            "ev.event_time as eventTime, ev.created_by as createdBy, ev.created_on as createdOn, ev.updated_on as updatedOn, ev.phone1 as phone1, ev.phone2 as phone2, ev.addressline1 as addressLine1, ev.addressline2 as addressLine2, ev.image_url as eventImageUrl," +
            "rg.region_id as regionId, rg.region_name as userRegionName, ct.city_id as cityId, ct.city_name as userCityName, st.state_id as stateId, st.state_name as userStateName, cnt.country_id as countryId, cnt.country_name as userCountryName,\n" +
            "up.image_url AS profileImageUrl "+
            "from shalom.event ev " +
            "JOIN shalom.category cat ON ev.category_id = cat.category_id " +
            "JOIN shalom.userprofile up ON up.user_id = ev.user_id " +
            "JOIN shalom.region rg ON ev.region_id=rg.region_id\n"+
            "JOIN shalom.city ct ON ct.city_id=ev.city_id\n" +
            "JOIN shalom.state st ON st.state_id=ev.state_id\n" +
            "JOIN shalom.country cnt ON cnt.country_id=ev.country_id\n" +
            " WHERE ev.user_id IN(SELECT uf.follow_id FROM shalom.userfollow uf WHERE uf.user_id=:userId AND uf.follow_flag=true) OR ev.user_id=:userId " +
            "ORDER BY ev.created_on DESC",  nativeQuery = true)
    List<IEvent> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "Update shalom.event\n" +
            "SET image_url=:imageUrl\n" +
            "where event_id=:eventId",  nativeQuery = true)
    void updateImageUrl(Long eventId, String imageUrl);

    @Query(value = "SELECT ev.event_id as eventId, ca.category_name as categoryName, ev.created_by as createdBy, up.image_url as profileImageUrl, up.user_id FROM shalom.event ev\n" +
            "JOIN shalom.category ca ON ca.category_id=ev.category_id\n" +
            "JOIN shalom.userprofile up ON up.user_id=ev.user_id\n" +
            "WHERE ev.user_id IN (SELECT uf.follow_id FROM shalom.userfollow uf WHERE uf.user_id=:userId AND uf.follow_flag=true) OR ev.user_id=:userId", nativeQuery = true)
    List<IEventNotify> findNotificationByUserId(Long userId);
}
