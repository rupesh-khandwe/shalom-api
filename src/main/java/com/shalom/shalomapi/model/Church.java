package com.shalom.shalomapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;

@Data
@Entity
@Table(name="church", schema="shalom")
public class Church {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "church_id")
    private Long churchId;
    private Long userId;
    private String churchName;
    private String churchWebsiteUrl;
/*    @Getter
    private LocalTime churchTime;*/
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
    //@DateTimeFormat(iso = DateTimeFormatter.ISO_LOCAL_DATE_TIME)
   // @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedOn;

    public Church(){

    }

    @Override
    public String toString() {
        return "Church{" +
                "churchId=" + churchId +
                ", userId=" + userId +
                ", churchName='" + churchName + '\'' +
                ", churchWebsiteUrl='" + churchWebsiteUrl + '\'' +
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
                '}';
    }
}
