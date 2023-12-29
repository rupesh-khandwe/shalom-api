package com.shalom.shalomapi.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="city", schema="shalom")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    @Getter
    private Long cityId;
    @Getter
    private Long stateId;
    @Getter
    private Long countryId;
    @Getter
    private String cityName;

    public City(){

    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", stateId=" + stateId +
                ", countryId=" + countryId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
