package com.shalom.shalomapi.model;

import java.time.LocalDateTime;

public interface IChurch {

    Long getChurchId();
    Long getUserId();
    String getChurchName();
    String getChurchWebsiteUrl();
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
