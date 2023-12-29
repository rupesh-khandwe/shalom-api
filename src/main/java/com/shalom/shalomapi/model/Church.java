package com.shalom.shalomapi.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Timer;

@Entity
@Table(name="church", schema="shalom")
public class Church {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "church_id")
    @Getter
    private Long churchId;
    @Getter
    private String churchName;
    @Getter
    private String churchWebsiteUrl;
    @Getter
    private LocalTime churchTime;
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

    public Church(){

    }

    @Override
    public String toString() {
        return "Church{" +
                ", churchId='" + churchId + '\'' +
                ", churchName='" + churchName + '\'' +
                ", churchWebsiteUrl='" + churchWebsiteUrl + '\'' +
                ", churchtime=" + churchTime +
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
