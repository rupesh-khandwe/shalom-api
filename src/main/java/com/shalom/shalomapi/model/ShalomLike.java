package com.shalom.shalomapi.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="shalomlike", schema="shalom")
public class ShalomLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shalom_like_id")
    @Getter
    private Long shalomLikeId;
    @Getter
    private Long userId;
    @Getter
    private Long shalomId;
    @Getter
    private Boolean shalomFlag;
    @Getter
    private LocalDateTime createdOn;

    public ShalomLike(){

    }

    @Override
    public String toString() {
        return "ShalomLike{" +
                "shalomLikeId=" + shalomLikeId +
                ", userId=" + userId +
                ", shalomId=" + shalomId +
                ", shalomFlag=" + shalomFlag +
                ", createdOn=" + createdOn +
                '}';
    }
}
