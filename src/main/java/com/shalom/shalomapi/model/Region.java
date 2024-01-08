package com.shalom.shalomapi.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="region", schema="shalom")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "region_id")
    @Getter
    private Long regionId;
    @Getter
    private Long cityId;
    @Getter
    private String pincode;
    @Getter
    private String regionName;

    public Region(){

    }

    @Override
    public String toString() {
        return "Region{" +
                "regionId=" + regionId +
                ", cityId=" + cityId +
                ", pincode='" + pincode + '\'' +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
