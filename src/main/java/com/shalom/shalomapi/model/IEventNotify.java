package com.shalom.shalomapi.model;

import java.time.LocalDateTime;

public interface IEventNotify {
    Long getEventId();
    String getCategoryName();
    String getCreatedBy();
    String getProfileImageUrl();

}
