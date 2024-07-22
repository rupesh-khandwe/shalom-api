package com.shalom.shalomapi.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Data
public class EventDTO {


    private Long eventId;
    private Long userId;
    private Long categoryId;
    private String title;
    private String description;
    private String eventDate;
    private String eventTime;
    private String phone1;
    private String phone2;
    private String addressline1;
    private String addressline2;
    private Long countryId;
    private Long stateId;
    private Long cityId;
    private Long regionId;
    private String createdBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdOn;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedOn;
    private String[] imageUrl;

    public EventDTO(){

    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "eventId=" + eventId +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", addressline1='" + addressline1 + '\'' +
                ", addressline2='" + addressline2 + '\'' +
                ", countryId=" + countryId +
                ", stateId=" + stateId +
                ", cityId=" + cityId +
                ", regionId=" + regionId +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", imageUrl=" + Arrays.toString(imageUrl) +
                '}';
    }
}
