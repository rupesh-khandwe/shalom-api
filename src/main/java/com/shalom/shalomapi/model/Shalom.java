package com.shalom.shalomapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name="shalom", schema="shalom")
public class Shalom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shalom_id")
    private Long shalomId;
    private Long userId;
    private Boolean shalomFlag;
    private String shalom;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private String imageUrl;
    private String videoUrl;
    private String userName;

    public Shalom(){

    }

    @Override
    public String toString() {
        return "Shalom{" +
                "shalomId=" + shalomId +
                ", userId=" + userId +
                ", shalomFlag=" + shalomFlag +
                ", shalom='" + shalom + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
