package com.shalom.shalomapi.model;

import java.time.LocalDateTime;

public interface IUserFollow {

    Long getFollowSeqId();
    Long getUserId();
    Long getFollowId();
    Boolean getFollowFlag();
    String getFirstName();
    String getLastName();

}
