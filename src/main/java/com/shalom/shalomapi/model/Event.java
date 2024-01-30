package com.shalom.shalomapi.model;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name="event", schema="shalom")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
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
    private String countryId;
    private String stateId;
    private String cityId;
    private String regionId;
    private String createdBy;

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
                ", eventDate='" + eventDate + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", addressline1='" + addressline1 + '\'' +
                ", addressline2='" + addressline2 + '\'' +
                ", countryId='" + countryId + '\'' +
                ", stateId='" + stateId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", regionId='" + regionId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
