package com.shalom.shalomapi.model;

import java.time.LocalDateTime;

public interface IShalomComment {

    String getUserName();
    String getShalomComment();
    LocalDateTime getCreatedOn();
    String getImageUrl();

}
