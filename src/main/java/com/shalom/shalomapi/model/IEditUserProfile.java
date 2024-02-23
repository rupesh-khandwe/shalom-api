package com.shalom.shalomapi.model;

public interface IEditUserProfile {


    String getUserEmail();
    String getUserFirstName();
    String getUserMiddleName();
    String getUserLastName();
    String getUserPhone1();
    String getUserPhone2();
    String getUserAddressLine1();
    String getUserAddressLine2();
    String getUserName();
    Long getRegionId();
    String getUserRegionName();
    Long getCityId();
    String getUserCityName();
    Long getStateId();
    String getUserStateName();
    Long getCountryId();
    String getUserCountryName();
}
