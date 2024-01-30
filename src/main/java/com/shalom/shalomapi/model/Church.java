package com.shalom.shalomapi.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private String countryId;
    private String stateId;
    private String cityId;
    private String regionId;
    private String createdBy;

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
                ", countryId='" + countryId + '\'' +
                ", stateId='" + stateId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", regionId='" + regionId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
