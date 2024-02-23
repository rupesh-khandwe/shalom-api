package com.shalom.shalomapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="userprofile", schema="shalom")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private Character gender;
    private String phone1;
    private String phone2;
    private String addressLine1;
    private String addressLine2;
    private Long countryId;
    private Long stateId;
    private Long cityId;
    private Long regionId;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private LocalDateTime lastLogin;
    private String userName;
    private String password;

    public UserProfile(){

    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", countryId='" + countryId + '\'' +
                ", stateId='" + stateId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", regionId='" + regionId + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", lastLogin=" + lastLogin +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
