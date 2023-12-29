package com.shalom.shalomapi.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="shalom", schema="shalom")
public class Shalom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shalom_id")
    @Getter
    private Long shalomId;
    @Getter
    private Long userId;
    @Getter
    private Boolean shalomFlag;
    @Getter
    private String shalom;
    @Getter
    private String createdOn;
    @Getter
    private String updatedOn;
    @Getter
    private String imageUrl;
    @Getter
    private String videoUrl;
    @Getter
    private Long likeCount;
    @Getter
    private Long commentCount;

    public Shalom(){

    }

    @Override
    public String toString() {
        return "Shalom{" +
                "shalomId=" + shalomId +
                ", userId=" + userId +
                ", shalomFlag=" + shalomFlag +
                ", shalom='" + shalom + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                '}';
    }
}
