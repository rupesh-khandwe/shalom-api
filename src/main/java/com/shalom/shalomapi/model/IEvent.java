package com.shalom.shalomapi.model;

import java.time.LocalDateTime;

public interface IEvent {
    Long getEventId();
    Long getUserId();
    Long getCategoryId();
    String getTitle();
    String getDescription();
    String getEventDate();
    String getEventTime();
    String getPhone1();
    String getPhone2();
    String getAddressLine1();
    String getAddressLine2();
    LocalDateTime getCreatedOn();
    LocalDateTime getUpdatedOn();
    Long getRegionId();
    String getUserRegionName();
    Long getCityId();
    String getUserCityName();
    Long getStateId();
    String getUserStateName();
    Long getCountryId();
    String getUserCountryName();
    String getCreatedBy();
}
