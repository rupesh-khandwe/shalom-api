package com.shalom.shalomapi.model;

import java.time.LocalDateTime;

public interface IShalomLikeComment {

    Long getShalomId();
    Long getUserId();
    Boolean getShalomFlag();
    String getShalom();
    LocalDateTime getCreatedOn();
    LocalDateTime getUpdatedOn();
    String getImageUrl();
    String getVideoUrl();
    Long getLikeCount();
    Long getCommentCount();
    String getUserName();

    Boolean getLikeFlag();
}
