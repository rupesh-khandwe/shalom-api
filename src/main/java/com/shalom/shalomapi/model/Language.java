package com.shalom.shalomapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="language", schema="shalom")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "language_id")
    private Long languageId;
    private String languageName;

    public Language(){

    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}
