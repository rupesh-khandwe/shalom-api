package com.shalom.shalomapi.dto;

import lombok.Data;

@Data
public class UserProfileDTO {

    private Long userId;
    private String profilePic;

    public UserProfileDTO(){

    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "userId=" + userId +
                ", profilePic='" + profilePic + '\'' +
                '}';
    }
}
