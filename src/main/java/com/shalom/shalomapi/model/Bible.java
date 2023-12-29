package com.shalom.shalomapi.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="bible", schema="shalom")
public class Bible {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter
    private Long id;
    @Getter
    private String bibleId;
    @Getter
    private String bibleName;
    @Getter
    private String nameLocal;
    @Getter
    private String abbreviation;
    @Getter
    private String description;
    @Getter
    private String descriptionLocal;
    @Getter
    private String languageName;
    @Getter
    private String languageScript;
    @Getter
    private String countryName;

    public Bible(){

    }

    @Override
    public String toString() {
        return "Bible{" +
                "id=" + id +
                ", bibleId='" + bibleId + '\'' +
                ", bibleName='" + bibleName + '\'' +
                ", nameLocal='" + nameLocal + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", description='" + description + '\'' +
                ", descriptionLocal='" + descriptionLocal + '\'' +
                ", languageName='" + languageName + '\'' +
                ", languageScript='" + languageScript + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
