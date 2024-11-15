package com.shalom.shalomapi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="appversion", schema="shalom")
public class AppVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String versionNumber;

}