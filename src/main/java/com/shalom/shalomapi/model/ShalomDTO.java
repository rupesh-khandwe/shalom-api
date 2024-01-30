package com.shalom.shalomapi.model;

import lombok.Data;

@Data
public class ShalomDTO {

    private Long userId;
    private String shalom;
    private String userName;

    public ShalomDTO(){

    }

    @Override
    public String toString() {
        return "ShalomDTO{" +
                "userId=" + userId +
                ", shalom='" + shalom + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
