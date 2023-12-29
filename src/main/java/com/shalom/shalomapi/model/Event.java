package com.shalom.shalomapi.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="event", schema="shalom")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    @Getter
    private Long eventId;
    @Getter
    private Long userId;
    @Getter
    private Long categoryId;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private LocalDate eventDate;
    @Getter
    private LocalTime eventTime;
    @Getter
    private String phone1;
    @Getter
    private String phone2;
    @Getter
    private String addressline1;
    @Getter
    private String addressline2;
    @Getter
    private String regionId;
    @Getter
    private String createdBy;
    @Getter
    private String createdOn;
    @Getter
    private String updatedOn;

    public Event(){

    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", eventDate=" + eventDate +
                ", eventTime=" + eventTime +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", addressline1='" + addressline1 + '\'' +
                ", addressline2='" + addressline2 + '\'' +
                ", regionId='" + regionId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                '}';
    }
}
