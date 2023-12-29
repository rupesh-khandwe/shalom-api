package com.shalom.shalomapi.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="country", schema="shalom")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    @Getter
    private Long countryId;
    @Getter
    private String countryName;

    public Country(){

    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
